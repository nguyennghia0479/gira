package cybersoft.javabackend.java18.gira.security.dto;

import cybersoft.javabackend.java18.gira.security.validation.annotation.MustBeExistedUser;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    @MustBeExistedUser
    public String username;
    public String password;
}
