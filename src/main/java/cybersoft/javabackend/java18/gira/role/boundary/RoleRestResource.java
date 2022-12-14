package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.RoleDTO;
import cybersoft.javabackend.java18.gira.role.service.RoleService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleRestResource {
    private final RoleService service;

    public RoleRestResource(RoleService roleService) {
        this.service = roleService;
    }

    @GetMapping
    public Object findAll() {
        return ResponseUtils.get(service.findAllDTO(RoleDTO.class), HttpStatus.OK);
    }

    @GetMapping("/paging")
    public Object findAllDtoPaging(@RequestParam("size") int size,
                                   @RequestParam("index") int index) {
        return ResponseUtils.get(service.findAllDTO(Pageable.ofSize(size).withPage(index), RoleDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public Object save(@RequestBody @Valid RoleDTO roleDTO) {
        return ResponseUtils.get(service.save(roleDTO), HttpStatus.CREATED);
    }

    @PostMapping("{role-id}/add-operation")
    public ResponseEntity<ResponseDTO> addOperation(@RequestBody List<UUID> ids,
                                                    @PathVariable("role-id") UUID roleId) {
        return ResponseUtils.get(service.addOperation(roleId, ids),HttpStatus.OK);
    }
}
