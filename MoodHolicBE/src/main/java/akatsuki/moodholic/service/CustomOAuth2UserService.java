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

    private final MemberDAO userRepository;

    @Autowired
    public CustomOAuth2UserService(MemberDAO userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }



        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        System.out.println("username = " + username);
        Member existData = userRepository.findByUsername(username);

        if (existData == null) {

            Member userEntity = new Member();
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setNickname(oAuth2Response.getName());
            userEntity.setImgPath(oAuth2Response.getThumbnail());
            userEntity.setProvider(oAuth2Response.getProvider());
            userEntity.setProviderCode(oAuth2Response.getProviderId());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);
        }
        else {

            existData.setEmail(oAuth2Response.getEmail());
            existData.setNickname(oAuth2Response.getName());
            existData.setImgPath(oAuth2Response.getThumbnail());

            userRepository.save(existData);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());

            return new CustomOAuth2User(userDTO);
        }
    }
}
