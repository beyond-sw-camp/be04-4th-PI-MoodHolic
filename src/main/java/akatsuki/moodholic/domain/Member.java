package akatsuki.moodholic.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="member")
@Data
public class Member {
    @Id
    @Column(name = "member_id")
    private long memberId;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "provider")
    private String provider;
    @Column(name = "email")
    private String email;
}