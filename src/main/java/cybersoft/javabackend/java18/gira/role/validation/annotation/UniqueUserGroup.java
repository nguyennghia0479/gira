package cybersoft.javabackend.java18.gira.role.validation.annotation;

import cybersoft.javabackend.java18.gira.role.validation.validator.UniqueUserGroupValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUserGroupValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UniqueUserGroup {
    String message() default "{usergroup.unique.existed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
