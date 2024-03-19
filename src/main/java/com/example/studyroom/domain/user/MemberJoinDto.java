package com.example.studyroom.domain.user;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.studyroom.domain.user.Member}
 */
@Builder
public record MemberJoinDto(
        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])[a-z0-9]{3,16}$")
        String username,
        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
        String password,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "^[가-힣]{2,6}$")
        String name,
        @NotNull
        @Past
        LocalDate birth,
        @NotNull
        Education education
) implements Serializable {

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .birth(birth)
                .education(education)
                .build();
    }
}