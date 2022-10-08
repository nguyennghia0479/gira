package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupDTO;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import cybersoft.javabackend.java18.gira.role.service.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usergroups")
public class UserGroupResResource {
    private final UserGroupService service;

    public UserGroupResResource(UserGroupService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        return ResponseUtils.get(service.findAllDTO(UserGroupDTO.class), HttpStatus.OK);
    }

    @GetMapping("/include-users")
    public ResponseEntity<ResponseDTO> findAllUserGroupIncludeUsers() {
        return ResponseUtils.get(service.findAllDtoIncludeUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid UserGroupDTO dto) {
        return ResponseUtils.get(service.save(dto, UserGroup.class, UserGroupDTO.class), HttpStatus.CREATED);
    }

    @PostMapping("{user-group-id}/add-users")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody List<UUID> ids,
                                                    @PathVariable("user-group-id") UUID userGroupId) {
        return ResponseUtils.get(service.addUser(userGroupId, ids),HttpStatus.OK);
    }
}
