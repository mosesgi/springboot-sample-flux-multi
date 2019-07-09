package com.moses.boot.sample.validation;

import com.moses.boot.sample.validation.constraints.ValidCardNumber;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.StringTokenizer;

public class ValidCardNumberConstraintValidator implements ConstraintValidator<ValidCardNumber, String> {
    @Override
    public void initialize(ValidCardNumber validCardNumber) {
    }

    /**
     * 前缀必须以"JMC-"开头，后缀必须是数字
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String[] parts = StringUtils.delimitedListToStringArray(value, "-");
        // 或者使用JDK new StringTokenizer(value, "-");
        // Apache commons-lang StringUtils
        if(parts.length != 2){
            return false;
        }
        String prefix = parts[0];
        String suffix = parts[1];
        boolean isValidPrefix = "JMC".equals(prefix);
        boolean isValidInteger = NumberUtils.isDigits(suffix);

        return isValidPrefix && isValidInteger;
    }
}
