package akatsuki.moodholic.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    private SecretKey key;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // AccessToken 생성
    public String createAccessToken(String username, String role) {
        long expirationTimeLong = 1000 * 60 * 30; // 30분
        return createToken(username, role, expirationTimeLong);
    }

    // RefreshToken 생성
    public String createRefreshToken(String username, String role) {
        long expirationTimeLong = 1000 * 60 * 60 * 24 * 7; // 7일
        return createToken(username, role, expirationTimeLong);
    }

    private String createToken(String username, String role, long expirationTime) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 만료 확인
    public Boolean isExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 토큰에서 만료 시간 추출
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // 토큰에서 클레임 추출
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // 토큰에서 사용자 이름 추출
    public String getUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }

    // 토큰에서 역할 추출
    public String getRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }
}
