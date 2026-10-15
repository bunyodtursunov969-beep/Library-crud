package uz.pdp.entity;

import lombok.*;
import uz.pdp.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthUser {

    private Integer id;
    private String fullName;
    private String username;
    private String password;
    private Role role;

}
