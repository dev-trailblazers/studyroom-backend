package com.example.studyroom.domain.study.dto;

import com.example.studyroom.domain.study.StudyGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link StudyGroup}
 */
public record RequestStudyGroupDto(
        @NotBlank String name,
        @NotBlank String type,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull byte headcount,
        @NotNull boolean isRecruit
) implements Serializable {
    public static StudyGroup toEntity(RequestStudyGroupDto dto){
        return StudyGroup.builder()
                .name(dto.name)
                .type(dto.type)
                .startDate(dto.startDate)
                .endDate(dto.endDate)
                .headcount(dto.headcount)
                .isRecruit(dto.isRecruit)
                .build();
    }


    public static RequestStudyGroupDto of(String name, String type, LocalDate startDate,
                                          LocalDate endDate, byte headcount, boolean isRecruit){
        return new RequestStudyGroupDto(name, type, startDate, endDate, headcount, isRecruit);
    }
}