package cybersoft.javabackend.java18.gira.role.boundary;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.role.dto.OperationDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/operations")
public class OperationRestResource {
    private final OperationService service;

    public OperationRestResource(OperationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        return ResponseUtils.get(service.findAllDTO(OperationDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid OperationDTO dto) {
        return ResponseUtils.get(service.save(dto, Operation.class, OperationDTO.class), HttpStatus.CREATED);
    }


}
