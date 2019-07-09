package com.moses.boot.sample.validation.constraints;

import com.moses.boot.sample.validation.ValidCardNumberConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 合法卡号校验
 * 需要通过工号的前缀和后缀来判断。前缀必须以"JMC-"开头，后缀必须是数字
 */
@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ValidCardNumberConstraintValidator.class})
public @interface ValidCardNumber {

    String message() default "{com.moses.boot.constraints.InvalidCardNum.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
