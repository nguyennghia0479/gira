package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import cybersoft.javabackend.java18.gira.role.service.UserGroupService;
import cybersoft.javabackend.java18.gira.security.authorization.GiraOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-groups")
public class UserGroupResResource {
    private final UserGroupService service;

    public UserGroupResResource(UserGroupService service) {
        this.service = service;
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "findAllUserGroup", type= Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findAll() {
        return ResponseUtils.get(service.findAllDTO(UserGroupDTO.class), HttpStatus.OK);
    }

    @GetMapping("/include-users")
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "findAllUserGroupIncludeUsers", type= Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findAllUserGroupIncludeUsers() {
        return ResponseUtils.get(service.findAllDtoIncludeUsers(), HttpStatus.OK);
    }

    @GetMapping("/include-roles")
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "findAllUserGroupIncludeRoles", type= Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findAllUserGroupIncludeRoles() {
        return ResponseUtils.get(service.findAllDtoIncludeRoles(), HttpStatus.OK);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "saveUserGroup", type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid UserGroupDTO dto) {
        return ResponseUtils.get(service.save(dto, UserGroup.class, UserGroupDTO.class), HttpStatus.CREATED);
    }

    @PostMapping("{user-group-id}/add-users")
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "addUsers", type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> addUsers(@RequestBody List<UUID> ids,
                                               @PathVariable("user-group-id") UUID userGroupId) {
        return ResponseUtils.get(service.addUser(userGroupId, ids), HttpStatus.OK);
    }

    @PostMapping("{user-group-id}/add-roles")
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "addRoles", type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> addRoles(@RequestBody List<UUID> ids,
                                                @PathVariable("user-group-id") UUID userGroupId) {
        return ResponseUtils.get(service.addRole(userGroupId, ids), HttpStatus.OK);
    }
}
