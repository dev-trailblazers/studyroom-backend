package com.example.studyroom.domain.study.dto;

import com.example.studyroom.domain.study.StudyGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.studyroom.domain.study.StudyGroup}
 */
public record StudyGroupDto(
        Long id,
        String name,
        String type,
        LocalDate startDate,
        LocalDate endDate,
        byte headcount,
        boolean isRecruit
) implements Serializable {

    public static StudyGroupDto of(Long id, String name, String type, LocalDate startDate,
                                   LocalDate endDate, byte headcount, boolean isRecruit){
        return new StudyGroupDto(id, name, type, startDate, endDate, headcount, isRecruit);
    }

    public static StudyGroupDto fromEntity(StudyGroup studyGroup){
        return StudyGroupDto.of(
                studyGroup.getId(),
                studyGroup.getName(),
                studyGroup.getType(),
                studyGroup.getStartDate(),
                studyGroup.getEndDate(),
                studyGroup.getHeadcount(),
                studyGroup.isRecruit()
        );
    }
}