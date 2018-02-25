package org.verlet.validator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义验证注解
 *
 * @author verlet
 * @date 2018/2/8
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
//校验
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
}
