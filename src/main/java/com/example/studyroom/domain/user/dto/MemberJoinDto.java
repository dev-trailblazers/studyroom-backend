package com.example.studyroom.domain.user.dto;

import com.example.studyroom.domain.user.Education;
import com.example.studyroom.domain.user.Member;
import com.example.studyroom.validation.MemberEmail;
import com.example.studyroom.validation.Password;
import com.example.studyroom.validation.Username;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.studyroom.domain.user.Member}
 */
@Builder
public record MemberJoinDto(
        @Username
        String username,
        @Password
        String password,
        @MemberEmail
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
                .role(Member.RoleType.USER)
                .build();
    }
}