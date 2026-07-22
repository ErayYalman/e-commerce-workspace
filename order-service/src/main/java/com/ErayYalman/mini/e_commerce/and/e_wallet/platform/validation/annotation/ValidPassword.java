package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.validation.validator.PasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented //bu anotasyonun javadoc'ta görünmesini sağlar
@Constraint(validatedBy = PasswordValidator.class) //bu anotasyonun hangi validator sınıfı tarafından doğrulanacağını belirtir
@Target(ElementType.FIELD) //bu anotasyonun hangi elemanlara uygulanabileceğini belirtir. FIELD, METHOD, PARAMETER gibi değerler alabilir
//field demek, bu anotasyonun bir sınıfın alanına uygulanabileceğini belirtir yani sınıf üzerinde kullanılabilir
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword { //@interface, bir anotasyon tanımlamak için kullanılır. Bu anotasyonun adı ValidPassword olacak

    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

        
}
