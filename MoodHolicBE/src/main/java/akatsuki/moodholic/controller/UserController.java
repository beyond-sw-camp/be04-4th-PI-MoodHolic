package akatsuki.moodholic.controller;

import akatsuki.moodholic.dto.CustomOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/userinfo")
    public String getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof OAuth2User) {
                OAuth2User oAuth2User = (OAuth2User) principal;
                // OAuth2User를 통해 사용자 정보에 접근
                // 사용자 정보는 Map 형태로 제공됩니다.
                String email = oAuth2User.getAttribute("email");
                return "User Info: Email = " + email;
            } else if (principal instanceof CustomOAuth2User) {
                CustomOAuth2User customOAuth2User = (CustomOAuth2User) principal;
                // CustomOAuth2User를 통해 사용자 정보에 접근
                String email = customOAuth2User.getEmail();
                return "User Info: Email = " + email;
            } else {
                return "사용자가 인증되지 않았습니다.";
            }
        } else {
            return "사용자가 인증되지 않았습니다.";
        }
    }

}
