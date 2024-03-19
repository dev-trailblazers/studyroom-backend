package com.example.studyroom.validation;

import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@NotBlank
@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
public @interface Password {
    String message() default "비밀번호는 영어 대소문자와 숫자, 특수문자를 하나씩 포함한 8~16자리입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
