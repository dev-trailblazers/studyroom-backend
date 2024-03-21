package com.example.studyroom.domain.auth;

import com.example.studyroom.validation.MemberEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record EmailVerificationDto(
        @MemberEmail String email,
        @NotBlank
        @Length(min = 6, max = 6) String code
) {
}
