package gr.infoteam.workshop_spring_boot.features.user.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = CustomPasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPasswordValidator {

    String message() default
            "Your password should be between 8 and 20 characters long, and include at least one "
                    + "uppercase letter, one lowercase letter, one number, and one special character.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
