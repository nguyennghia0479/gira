package cybersoft.javabackend.java18.gira.role.dto;

import cybersoft.javabackend.java18.gira.role.model.Operation;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleWithOperationDTO {
    private UUID id;
    @Size(min = 5, max = 100, message = "{service.name.size}")
    @NotBlank(message = "{service.name.blank}")
    private String name;

    @Size(min = 3, max = 10, message = "{service.code.size}")
    @NotBlank(message = "{service.code.blank}")
    private String code;

    @NotBlank(message = "{service.description.blank}")
    private String description;

    private Operation.Type type;

    private Set<OperationDTO> operations;
}
