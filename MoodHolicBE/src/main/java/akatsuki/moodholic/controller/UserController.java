package akatsuki.moodholic.controller;

import akatsuki.moodholic.domain.Member;
import akatsuki.moodholic.repository.MemberDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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
            String email = getEmailFromToken(token);
            String role = getProviderFromToken(token);
            // Log extracted information
            System.out.println("Email extracted: " + email);
            System.out.println("Provider extracted: " + role);

            if (email != null && role != null) {
                Optional<Member> optionalMember = memberDAO.findByEmailAndProvider(email, role);
                if (optionalMember.isPresent()) {
                    Member member = optionalMember.get();
                    return ResponseEntity.ok("사용자 정보 조회 성공: User ID = " + email + ", Member Info = " + member.toString());
                } else {
                    return ResponseEntity.ok("사용자 정보가 존재하지 않습니다.");
                }
            } else {
                return ResponseEntity.badRequest().body("토큰에서 사용자 정보를 추출할 수 없습니다.");
            }
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

    private String getEmailFromToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody().get("email", String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private String getProviderFromToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody().get("role", String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
