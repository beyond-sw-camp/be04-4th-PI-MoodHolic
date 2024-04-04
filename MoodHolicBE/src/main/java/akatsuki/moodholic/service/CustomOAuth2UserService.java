package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Member;
import akatsuki.moodholic.dto.*;
import akatsuki.moodholic.repository.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberDAO memberDAO;

    @Autowired
    public CustomOAuth2UserService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());

            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-512");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            byte[] digest = md.digest(oAuth2Response.getProviderId().getBytes());
            long providerId = new BigInteger(digest).longValue();
            System.out.println("sha256: " +providerId );
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());

        }

        else {

            return null;
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = md.digest(oAuth2Response.getProviderId().getBytes());
        long providerId;
        if(registrationId.equals("naver")||registrationId.equals("google")){

            providerId = new BigInteger(digest).longValue();
            if (providerId < 0) {
                providerId = -providerId;
            }
        } else  {
            providerId = Long.parseLong(oAuth2Response.getProviderId());

        }


        System.out.println("sha512: " + providerId );


        String providerCode = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        System.out.println("providerCode = " + providerCode);
        Member existData = memberDAO.findByProviderCode(providerCode);

        if (existData == null) {
            Member member = Member.builder()
                    .memberId(providerId)
                    .nickname(oAuth2Response.getName())
                    .email(oAuth2Response.getEmail())
                    .role(oAuth2Response.getProvider()) // 역할 설정 수정이 필요할 수 있습니다
                    .imgPath(oAuth2Response.getThumbnail())
                    .providerCode(providerCode)
                    .build();



            member = memberDAO.save(member);

            UserDTO userDTO = UserDTO.builder()
                    .role(oAuth2Response.getProvider()) // 역할 설정 수정이 필요할 수 있습니다
                    .name(oAuth2Response.getName())
                    .providerCode(providerCode)
                    .build();

            return new CustomOAuth2User(userDTO);
        } else {
            existData.setEmail(oAuth2Response.getEmail());
            existData.setNickname(oAuth2Response.getName());
            existData.setImgPath(oAuth2Response.getThumbnail());

            memberDAO.save(existData);

            UserDTO userDTO = UserDTO.builder()
                    .role(existData.getRole()) // 역할 설정 확인
                    .name(oAuth2Response.getName())
                    .providerCode(providerCode)
                    .build();

            return new CustomOAuth2User(userDTO);
        }

    }
}
