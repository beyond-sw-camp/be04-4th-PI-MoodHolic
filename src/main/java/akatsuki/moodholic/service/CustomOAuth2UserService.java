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
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = getOAuth2Response(oAuth2User, registrationId);
        if (oAuth2Response == null) return null;

        String providerCode = oAuth2Response.getProviderId();
        Optional<Member> optionalMember = memberDAO.findByProviderCode(providerCode);

        Member member;
        if (optionalMember.isPresent()) {
            member = updateExistingMember(optionalMember.get(), oAuth2Response);
        } else {
            member = createNewMember(oAuth2Response);
        }

        if (member == null) {
            throw new IllegalStateException("Member cannot be null");
        }

        return new CustomOAuth2User(member);
    }

    private Member updateExistingMember(Member member, OAuth2Response response) {
        member.setNickname(response.getNickname());
        member.setImgPath(response.getThumbnail());
        return memberDAO.save(member);
    }

    private Member createNewMember(OAuth2Response response) {
        Member newMember = Member.builder()
                .email(response.getEmail())
                .nickname(response.getNickname())
                .imgPath(response.getThumbnail())
                .provider(response.getProvider())
                .providerCode(response.getProviderId())
                .role("ROLE_USER")
                .build();
        return memberDAO.save(newMember);
    }

    private OAuth2Response getOAuth2Response(OAuth2User oAuth2User, String registrationId) {
        if ("kakao".equals(registrationId)) {
            return new KakaoResponse(oAuth2User.getAttributes());
        } else if ("google".equals(registrationId)) {
            return new GoogleResponse(oAuth2User.getAttributes());
        }
        return null;
    }
}
