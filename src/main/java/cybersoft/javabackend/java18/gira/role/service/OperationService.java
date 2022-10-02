package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.OperationDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.repository.OperationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface OperationService extends GenericService<Operation, OperationDTO, UUID> {
}

@Service
class OperationServiceImpl implements OperationService {
    private final OperationRepository repository;
    private final GiraMapper mapper;

    public OperationServiceImpl(OperationRepository repository, GiraMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public JpaRepository<Operation, UUID> getRepository() {
        return repository;
    }

    @Override
    public ModelMapper getMapper() {
        return mapper;
    }

}
