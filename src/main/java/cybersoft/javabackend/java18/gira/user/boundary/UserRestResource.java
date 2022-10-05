package cybersoft.javabackend.java18.gira.user.boundary;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.user.dto.UserDTO;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRestResource {
    private final UserService service;

    public UserRestResource(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        return ResponseUtils.get(service.findAllDTO(UserDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid UserDTO dto) {
        return ResponseUtils.get(service.save(dto, User.class, UserDTO.class), HttpStatus.CREATED);
    }
}
