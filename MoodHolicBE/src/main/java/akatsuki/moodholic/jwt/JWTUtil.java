package akatsuki.moodholic.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey key;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret,
                   @Value("${spring.jwt.accessTokenExpirationTime}") long accessTokenExpirationTime,
                   @Value("${spring.jwt.refreshTokenExpirationTime}") long refreshTokenExpirationTime) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    // AccessToken 생성
    public String createAccessToken(String role, String email) {
        Date now = new Date();
        return Jwts.builder()
                .claim("role", role)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenExpirationTime))
                .signWith(key)
                .compact();
    }

    // RefreshToken 생성
    public String createRefreshToken(String role, String email) {
        Date now = new Date();
        return Jwts.builder()
                .claim("role", role)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenExpirationTime))
                .signWith(key)
                .compact();
    }

    // 토큰 만료 확인
    public boolean isExpired(String token) {
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

    // 토큰에서 역할 추출
    public String getRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // 토큰에서 이메일 추출
    public String getEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }
}
