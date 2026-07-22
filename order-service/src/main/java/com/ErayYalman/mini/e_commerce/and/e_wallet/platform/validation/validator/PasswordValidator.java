package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.validation.validator;

import java.util.regex.Pattern;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.validation.annotation.ValidPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,255}$");


    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        
        if (password == null) {
            return false;
        }

        boolean isValid = PASSWORD_PATTERN.matcher(password).matches();

        if (!isValid) {
            context.disableDefaultConstraintViolation(); //default mesajı devre dışı bırakır
            context.buildConstraintViolationWithTemplate("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
                    .addConstraintViolation();
        }

        return isValid;
    }

}
