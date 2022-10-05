package cybersoft.javabackend.java18.gira.role.dto;

import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueUserGroup;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@UniqueUserGroup
public class UserGroupDTO {
    private UUID id;

    @Size(min = 5, max = 100, message = "{usergroup.name.size}")
    @NotBlank(message = "{usergorup.name.blank}")
    private String name;

    @Size(min = 3, max = 10, message = "{usergroup.code.size}")
    @NotBlank(message = "{usergorup.code.blank}")
    private String code;

    private String description;
}
