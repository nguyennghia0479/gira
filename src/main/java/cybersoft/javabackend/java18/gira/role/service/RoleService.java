package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.RoleDTO;
import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface RoleService extends GenericService<Role, RoleDTO, UUID> {

    Role update(Role role, String code);

    void delete(String code);

    RoleDTO save(RoleDTO dto);
}

@Service
@Transactional
class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    private final GiraMapper mapper;

    public RoleServiceImpl(RoleRepository repository, GiraMapper mapper) {
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
    public JpaRepository<Role, UUID> getRepository() {
        return this.repository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.mapper;
    }
}
