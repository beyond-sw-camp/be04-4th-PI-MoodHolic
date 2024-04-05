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
    @Column(name = "member_id")
    private long memberId;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "provider")
    private String role;
    @Column(name = "email")
    private String email;
    @Column(name = "img_path")
    private String imgPath;
    @Column(name = "provider_code")
    private String providerCode;


}

