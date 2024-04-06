package akatsuki.moodholic.domain;


import jakarta.persistence.*;
import lombok.*;

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
    // 여기서부턴 provider 정보
    @Column(name = "provider")
    private String provider;
    @Column(name = "provider_code")
    private String providerCode;
    // provider + providerCode
    @Column(name = "username")
    private String username;

}

