package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.RoleDTO;
import cybersoft.javabackend.java18.gira.role.dto.RoleWithOperationDTO;
import cybersoft.javabackend.java18.gira.role.model.Operation;
import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService extends GenericService<Role, RoleDTO, UUID> {

    Role update(Role role, String code);

    void delete(String code);

    RoleDTO save(RoleDTO dto);

    RoleWithOperationDTO addOperation(UUID roleId, List<UUID> ids);

    List<RoleWithOperationDTO> findAllDtoIncludeOperations();
}

@Service
@Transactional
class RoleServiceImpl implements RoleService {
    private final OperationService service;

    private final RoleRepository repository;

    private final GiraMapper mapper;

    public RoleServiceImpl(OperationService service, RoleRepository repository, GiraMapper mapper) {
        this.service = service;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Role update(Role role, String code) {
        Optional<Role> optRole = repository.findByCode(code);
        if (optRole.isPresent()) {
            Role curRole = optRole.get();
            curRole.setName(role.getName());
            curRole.setDescription(role.getDescription());
            return repository.save(curRole);
        }
        return null;
    }

    @Override
    public void delete(String code) {
        repository.deleteByCode(code);
    }

    @Override
    public RoleDTO save(RoleDTO dto) {
        Role model = mapper.map(dto, Role.class);
        Role saveModel = repository.save(model);
        return mapper.map(saveModel, RoleDTO.class);
    }

    @Override
    public RoleWithOperationDTO addOperation(UUID roleId, List<UUID> ids) {
        Role curRole = repository.findById(roleId)
                .orElseThrow( () ->
                        new ValidationException("Role is not existed")
                );
        List<Operation> operations = service.findByIds(ids);
        operations.forEach(curRole::addOperation);
        return mapper.map(curRole, RoleWithOperationDTO.class);
    }

    @Override
    public List<RoleWithOperationDTO> findAllDtoIncludeOperations() {
        return repository.findAllWithOperations()
                .stream()
                .distinct()
                .map(model -> mapper.map(model, RoleWithOperationDTO.class))
                .toList();
    }

    @Override
    public JpaRepository<Role, UUID> getRepository() {
        return this.repository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.mapper;
    }
}
