package akatsuki.moodholic.jwt;

import akatsuki.moodholic.repository.RefreshRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    @Getter
    private final long accessTokenExpirationTime;
    @Getter
    private final long refreshTokenExpirationTime;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret,
                   @Value("${spring.jwt.accessTokenExpirationTime}") long accessTokenExpirationTime,
                   @Value("${spring.jwt.refreshTokenExpirationTime}") long refreshTokenExpirationTime) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }
    public Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }

    public List<GrantedAuthority> getAuthorities(String role) {
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("email", String.class);
    }

    public String getCategory(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("category", String.class);
    }

    public String getRole(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    public String getProvider(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("provider", String.class);
    }

    public boolean isExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public String createJwt(String category, String email, String role, String provider, long expiredMs) {
        return Jwts.builder()
                .claim("category", category)
                .claim("email", email)
                .claim("role", role)
                .claim("provider", provider)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }



    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
