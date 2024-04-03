package akatsuki.moodholic.member.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private long member_id;
    private String nickname;
    private String provider;
    private String email;
}
