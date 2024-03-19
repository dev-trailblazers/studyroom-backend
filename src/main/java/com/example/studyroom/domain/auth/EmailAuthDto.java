package com.example.studyroom.domain.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record EmailAuthDto(
        @NotBlank @Email String email,
        @NotBlank @Length(min = 6, max = 6) String code
) {
}
