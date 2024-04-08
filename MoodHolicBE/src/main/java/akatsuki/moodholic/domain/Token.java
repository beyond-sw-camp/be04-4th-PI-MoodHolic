package akatsuki.moodholic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Token")
public class Token {
    @Id
    private String email;
    private String accessToken;
    private String refreshToken;
    private Date issuedAt; // 토큰 발급 시간
    private Date accessTokenExpiresAt; // 액세스 토큰 만료 시간

    public void updateToken(String accessToken, String refreshToken, Date issuedAt, Date accessTokenExpiresAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.issuedAt = issuedAt;
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }
}
