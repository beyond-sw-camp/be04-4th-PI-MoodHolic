package akatsuki.moodholic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash(value = "RefreshEntity", timeToLive = 604800)
public class RefreshEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String refreshToken;
    private String provide;
    private String provideCode;
    private String expiration;
}
