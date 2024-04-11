package akatsuki.moodholic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash(value = "refreshToken", timeToLive = 604800)
public class RefreshEntity {

    @Id
    private String id;
    private String email;
    private String refreshToken;
    private String provide;
    private String provideCode;
    private String expiration;
}
