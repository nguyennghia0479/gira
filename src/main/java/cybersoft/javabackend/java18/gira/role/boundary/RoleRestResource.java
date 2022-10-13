package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.RoleDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.service.RoleService;
import cybersoft.javabackend.java18.gira.security.authorization.GiraOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleRestResource {
    private final RoleService service;

    public RoleRestResource(RoleService roleService) {
        this.service = roleService;
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "findAllRole", type= Operation.Type.FETCH)
    public Object findAll() {
        return ResponseUtils.get(service.findAllDTO(RoleDTO.class), HttpStatus.OK);
    }

    @GetMapping("/paging")
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "findAllRoleWithPaging", type= Operation.Type.FETCH)
    public Object findAllDtoPaging(@RequestParam("size") int size,
                                   @RequestParam("index") int index) {
        return ResponseUtils.get(service.findAllDTO(Pageable.ofSize(size).withPage(index), RoleDTO.class), HttpStatus.OK);
    }

    @GetMapping("/include-operations")
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "findAllRoleIncludeOperations", type= Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findAllDtoIncludeOperations() {
        return ResponseUtils.get(service.findAllDtoIncludeOperations(), HttpStatus.OK);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "saveRole", type = Operation.Type.SAVE_OR_UPDATE)
    public Object save(@RequestBody @Valid RoleDTO roleDTO) {
        return ResponseUtils.get(service.save(roleDTO), HttpStatus.CREATED);
    }

    @PostMapping("{role-id}/add-operation")
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "addOperations", type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> addOperations(@RequestBody List<UUID> ids,
                                                    @PathVariable("role-id") UUID roleId) {
        return ResponseUtils.get(service.addOperation(roleId, ids),HttpStatus.OK);
    }
}
