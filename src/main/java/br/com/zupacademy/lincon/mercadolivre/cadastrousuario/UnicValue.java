package br.com.zupacademy.lincon.mercadolivre.cadastrousuario;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnicValue {
    String message() default "{br.com.zupacademy.lincon.beanvalidation.uniquevalue}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> domainClass();
}
