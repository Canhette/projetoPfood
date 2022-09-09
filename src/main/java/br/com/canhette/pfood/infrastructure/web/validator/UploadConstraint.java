package br.com.canhette.pfood.infrastructure.web.validator;

import br.com.canhette.pfood.util.FileType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD  , ElementType.METHOD})
@Constraint(validatedBy = UploadValidator.class)
public @interface UploadConstraint {

    String message() default "Arquivo inválido";
    FileType[] acceptedTypes();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
