package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.OperationDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.service.OperationService;
import cybersoft.javabackend.java18.gira.security.authorization.GiraOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/operations")
public class OperationRestResource {
    private final OperationService service;

    public OperationRestResource(OperationService service) {
        this.service = service;
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "findAllOperation", type= Operation.Type.FETCH)
    public ResponseEntity<ResponseDTO> findAll() {
        return ResponseUtils.get(service.findAllDTO(OperationDTO.class), HttpStatus.OK);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "saveOperation", type = Operation.Type.SAVE_OR_UPDATE)
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid OperationDTO dto) {
        return ResponseUtils.get(service.save(dto, Operation.class, OperationDTO.class), HttpStatus.CREATED);
    }


}
