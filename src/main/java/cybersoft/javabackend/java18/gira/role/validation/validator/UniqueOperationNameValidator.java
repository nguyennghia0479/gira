package cybersoft.javabackend.java18.gira.role.validation.validator;

import cybersoft.javabackend.java18.gira.role.repository.OperationRepository;
import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueOperationName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueOperationNameValidator implements ConstraintValidator<UniqueOperationName, String> {
    private final OperationRepository repository;
    private String message;

    public UniqueOperationNameValidator(OperationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueOperationName constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        boolean isExisted = repository.existsByName(name);
        if(!isExisted)
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
