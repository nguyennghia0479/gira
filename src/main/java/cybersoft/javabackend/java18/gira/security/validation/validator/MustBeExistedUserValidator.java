package cybersoft.javabackend.java18.gira.security.validation.validator;

import cybersoft.javabackend.java18.gira.security.validation.annotation.MustBeExistedUser;
import cybersoft.javabackend.java18.gira.user.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MustBeExistedUserValidator implements ConstraintValidator<MustBeExistedUser, String> {
    private final UserRepository repository;
    private String message;

    public MustBeExistedUserValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(MustBeExistedUser constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean isExistedUser = repository.existsByUsername(username);
        if(isExistedUser)
            return true;

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
