package akatsuki.moodholic.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name="member")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long memberId;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    // Security 권한(관리자, 사용자 등등)
    @Column(name = "img_path")
    private String imgPath;
    @Column(name = "role")
    private String role;
    @Column(name = "provider")
    private String provider;
    @Column(name = "provider_code")
    private String providerCode;

}

