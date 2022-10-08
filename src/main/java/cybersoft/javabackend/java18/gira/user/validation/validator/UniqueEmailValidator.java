package cybersoft.javabackend.java18.gira.user.validation.validator;

import cybersoft.javabackend.java18.gira.user.repository.UserRepository;
import cybersoft.javabackend.java18.gira.user.validation.annotation.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository repository;
    private String message;

    public UniqueEmailValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean isExisted = repository.existsByEmail(email);
        if(!isExisted)
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
