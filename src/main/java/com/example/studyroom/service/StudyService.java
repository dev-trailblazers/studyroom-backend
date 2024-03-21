package com.example.studyroom.service;

import com.example.studyroom.domain.study.dto.StudyGroupDto;
import com.example.studyroom.repository.study.StudyParticipationRepository;
import com.example.studyroom.repository.study.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyGroupRepository studyGroupRepository;
    private final StudyParticipationRepository studyParticipationRepository;

    public void createStudyGroup(StudyGroupDto dto){
        studyGroupRepository.save(StudyGroupDto.toEntity(dto));
    }
}
