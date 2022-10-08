package cybersoft.javabackend.java18.gira.user.dto;

import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.validation.annotation.UniqueEmail;
import cybersoft.javabackend.java18.gira.user.validation.annotation.UniqueUsername;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private UUID id;

    @Size(min = 3, max = 100, message = "{user.username.size}")
    @NotBlank(message = "{user.username.blank}")
    @UniqueUsername
    private String username;

    @Size(min = 3, message = "{user.password.size}")
    @NotBlank(message = "{user.password.blank}")
    private String password;

    @Email(message = "{user.email.pattern}")
    @Size(min = 3, max = 100, message = "{user.email.size}")
    @NotBlank(message = "{user.email.blank}")
    @UniqueEmail
    private String email;

    @Size(min = 3, message = "{user.displayName.size}")
    @NotBlank(message = "{user.displayName.blank}")
    private String displayName;

    @Size(min = 3, message = "{user.fullName.size}")
    @NotBlank(message = "{user.fullName.blank}")
    private String fullName;

    @Size(min = 3, message = "{user.avatar.size}")
    @NotBlank(message = "{user.avatar.blank}")
    private String avatar;

    private User.Status status;
}
