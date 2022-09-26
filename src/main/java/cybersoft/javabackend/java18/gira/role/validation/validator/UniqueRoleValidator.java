package cybersoft.javabackend.java18.gira.role.validation.validator;

import cybersoft.javabackend.java18.gira.role.dto.RoleDTO;
import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.repository.RoleRepository;
import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueRoleValidator implements ConstraintValidator<UniqueRole, RoleDTO> {
    private final RoleRepository repository;
    private String message;

    public UniqueRoleValidator(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueRole constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(RoleDTO roleDTO, ConstraintValidatorContext context) {
        Optional<Role> roleName = repository.findByName(roleDTO.getName());
        Optional<Role> roleCode = repository.findByCode(roleDTO.getCode());
        if (roleName.isEmpty() && roleCode.isEmpty())
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
