package cybersoft.javabackend.java18.gira.role.validation.validator;

import cybersoft.javabackend.java18.gira.role.dto.UserGroupDTO;
import cybersoft.javabackend.java18.gira.role.repository.UserGroupRepository;
import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueUserGroup;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserGroupValidator implements ConstraintValidator<UniqueUserGroup, UserGroupDTO> {
    private final UserGroupRepository repository;
    private String message;

    public UniqueUserGroupValidator(UserGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueUserGroup constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserGroupDTO userGroupDTO, ConstraintValidatorContext context) {
        boolean nameExisted = repository.existsByName(userGroupDTO.getName());
        boolean codeExisted = repository.existsByCode(userGroupDTO.getCode());
        if(!nameExisted && !codeExisted)
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
