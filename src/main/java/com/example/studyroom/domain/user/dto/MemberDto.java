package com.example.studyroom.domain.user.dto;

import com.example.studyroom.domain.user.Gender;
import com.example.studyroom.domain.user.Member;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.studyroom.domain.user.Member}
 */
public record MemberDto(
        Long id,
        String username,
        String password,
        String email,
        String name,
        LocalDate birth,
        Gender gender,
        String profile_image
) implements Serializable {
    public static MemberDto fromEntity(Member member) {
        return new MemberDto(
                member.getId(),
                member.getUsername(),
                member.getPassword(),
                member.getEmail(),
                member.getName(),
                member.getBirth(),
                member.getGender(),
                member.getProfile_image()
        );
    }
}