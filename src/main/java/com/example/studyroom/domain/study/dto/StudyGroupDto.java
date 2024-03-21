package com.example.studyroom.domain.study.dto;

import com.example.studyroom.domain.study.StudyGroup;

import java.io.Serializable;

/**
 * DTO for {@link com.example.studyroom.domain.study.StudyGroup}
 */
public record StudyGroupDto(String name, String type) implements Serializable {
    public static StudyGroup toEntity(StudyGroupDto dto){
        return StudyGroup.builder()
                .name(dto.name())
                .type(dto.type())
                .build();
    }

    public static StudyGroupDto fromEntity(StudyGroup studyGroup){
        return new StudyGroupDto(studyGroup.getName(), studyGroup.getType());
    }
}