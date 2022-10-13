package cybersoft.javabackend.java18.gira.role.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupWithRoleDTO {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private Set<RoleDTO> roles;
}
