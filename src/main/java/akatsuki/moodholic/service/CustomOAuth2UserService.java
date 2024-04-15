package akatsuki.moodholic.service;

import akatsuki.moodholic.domain.Member;
import akatsuki.moodholic.dto.*;
import akatsuki.moodholic.jwt.JWTUtil;
import akatsuki.moodholic.repository.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberDAO memberDAO;
    private final RedisTemplate<String, String> redisTemplate;
    private final JWTUtil jwtUtil;

    @Autowired
    public CustomOAuth2UserService(MemberDAO userRepository, RedisTemplate<String, String> redisTemplate, JWTUtil jwtUtil) {
        this.memberDAO = userRepository;
        this.redisTemplate = redisTemplate;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 변수 재사용

        OAuth2Response oAuth2Response;
        if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String email = oAuth2Response.getEmail();
        String providerCode = oAuth2Response.getProviderId();
//        Optional<Member> optionalMember = memberDAO.findByEmailAndProviderId(email, providerId);
        Optional<Member> optionalMember = memberDAO.findByProviderCode(providerCode);

        Member member = null;
        if (optionalMember.isPresent()) {
            member = optionalMember.get();
            // 이미 존재하는 사용자에 대한 처리
            member.setNickname(oAuth2Response.getNickname());
            member.setImgPath(oAuth2Response.getThumbnail());
            memberDAO.save(member);
            System.out.println("이미 가입됨.: " + email + " 유저 정보를 업데이트 합니다.");
        } else {
            // 새로운 사용자 생성
            Member newMember = Member.builder()
                    .email(email)
                    .nickname(oAuth2Response.getNickname())
                    .imgPath(oAuth2Response.getThumbnail())
                    .provider(oAuth2Response.getProvider())
                    .providerCode(providerCode)
                    .role("ROLE_USER")
                    .build();
            memberDAO.save(newMember);
            System.out.println("신규 회원: " + email);
        }

        // 사용자 권한 정보를 설정하기 위해 Member 객체를 기반으로 CustomOAuth2User 객체 생성
        return new CustomOAuth2User(member);
    }
}
