package cybersoft.javabackend.java18.gira.role.service;

import cybersoft.javabackend.java18.gira.common.service.GenericService;
import cybersoft.javabackend.java18.gira.common.util.GiraMapper;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupDTO;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupWithRoleDTO;
import cybersoft.javabackend.java18.gira.role.dto.UserGroupWithUserDTO;
import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import cybersoft.javabackend.java18.gira.role.repository.UserGroupRepository;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

public interface UserGroupService extends GenericService<UserGroup, UserGroupDTO, UUID> {
    UserGroupWithUserDTO addUser(UUID userGroupId, List<UUID> ids);

    UserGroupWithRoleDTO addRole(UUID userGroupId, List<UUID> ids);

    List<UserGroupWithUserDTO> findAllDtoIncludeUsers();

    List<UserGroupWithRoleDTO> findAllDtoIncludeRoles();
}

@Service
@Transactional
class UserGroupServiceImpl implements UserGroupService {
    private final UserService userService;
    private final RoleService roleService;
    private final UserGroupRepository repository;
    private final GiraMapper mapper;

    UserGroupServiceImpl(UserService userService, RoleService roleService, UserGroupRepository repository, GiraMapper mapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public JpaRepository<UserGroup, UUID> getRepository() {
        return repository;
    }

    @Override
    public ModelMapper getMapper() {
        return mapper;
    }

    @Override
    public UserGroupWithUserDTO addUser(UUID userGroupId, List<UUID> ids) {
        UserGroup curUserGroup = repository.findById(userGroupId)
                .orElseThrow(() ->
                        new ValidationException("User group is not existed")
                );
        List<User> users = userService.findByIds(ids);
        users.forEach(curUserGroup::addUser);
        return mapper.map(curUserGroup, UserGroupWithUserDTO.class);
    }

    @Override
    public UserGroupWithRoleDTO addRole(UUID userGroupId, List<UUID> ids) {
        UserGroup curUserGroup = repository.findById(userGroupId)
                .orElseThrow(
                        () -> new ValidationException("User group is not existed")
                );
        List<Role> roles = roleService.findByIds(ids);
        roles.forEach(curUserGroup::addRole);
        return mapper.map(curUserGroup, UserGroupWithRoleDTO.class);
    }

    @Override
    public List<UserGroupWithUserDTO> findAllDtoIncludeUsers() {
        return repository.findAllWithUsers()
                .stream()
                .distinct()
                .map(model -> mapper.map(model, UserGroupWithUserDTO.class))
                .toList();
    }

    @Override
    public List<UserGroupWithRoleDTO> findAllDtoIncludeRoles() {
        return repository.findAllWithRoles()
                .stream()
                .distinct()
                .map(model -> mapper.map(model, UserGroupWithRoleDTO.class))
                .toList();
    }
}
