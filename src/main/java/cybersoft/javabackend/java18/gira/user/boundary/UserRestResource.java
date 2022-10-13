package cybersoft.javabackend.java18.gira.user.boundary;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.security.authorization.GiraOperation;
import cybersoft.javabackend.java18.gira.user.dto.UserDTO;
import cybersoft.javabackend.java18.gira.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestResource {
    private final UserService service;

    public UserRestResource(UserService service) {
        this.service = service;
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "findAllUser", type = Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findAll() {
        return ResponseUtils.get(service.findAllDTO(UserDTO.class), HttpStatus.OK);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "saveUser", type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid UserDTO dto) {
        return ResponseUtils.get(service.createUser(dto), HttpStatus.CREATED);
    }
}
