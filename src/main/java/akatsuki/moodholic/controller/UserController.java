package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.Member;
import akatsuki.moodholic.repository.MemberDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final MemberDAO memberDAO;
    private final SecretKey secretKey;

    @Autowired
    public UserController(MemberDAO memberDAO, @Value("${spring.jwt.secret}") String secret) {
        this.memberDAO = memberDAO;
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @GetMapping("/userinfo")
    public ResponseEntity<String> getUserInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);
        if (token != null) {
            try {
                Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
                String email = claims.getBody().get("email", String.class);
                String provider = claims.getBody().get("provider", String.class);
                logger.info("Email extracted: {}", email);
                logger.info("Provider extracted: {}", provider);

                if (email != null && provider != null) {
                    Optional<Member> optionalMember = memberDAO.findByEmailAndProvider(email, provider);
                    if (optionalMember.isPresent()) {
                        Member member = optionalMember.get();
                        return ResponseEntity.ok("사용자 정보 조회 성공: User ID = " + email + ", Member Info = " + member.toString());
                    } else {
                        return ResponseEntity.ok("사용자 정보가 존재하지 않습니다.");
                    }
                }
            } catch (Exception e) {
                logger.error("Token parsing error: {}", e.getMessage());
            }
            return ResponseEntity.badRequest().body("토큰에서 사용자 정보를 추출할 수 없습니다.");
        } else {
            return ResponseEntity.status(401).body("사용자가 인증되지 않았습니다.");
        }
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
