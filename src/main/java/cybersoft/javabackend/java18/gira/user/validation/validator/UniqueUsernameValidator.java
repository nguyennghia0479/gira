package cybersoft.javabackend.java18.gira.user.validation.validator;

import cybersoft.javabackend.java18.gira.user.repository.UserRepository;
import cybersoft.javabackend.java18.gira.user.validation.annotation.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserRepository repository;
    private String message;

    public UniqueUsernameValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean isExisted = repository.existsByUsername(username);
        if(!isExisted)
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
