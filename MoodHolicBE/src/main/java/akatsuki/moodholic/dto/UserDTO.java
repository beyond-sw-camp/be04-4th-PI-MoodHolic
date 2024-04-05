package akatsuki.moodholic.dto;

import io.swagger.v3.oas.annotations.info.Contact;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String role;
    private String name;
    private String providerCode;


}
