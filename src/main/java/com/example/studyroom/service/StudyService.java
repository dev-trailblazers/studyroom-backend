package com.example.studyroom.service;

import com.example.studyroom.domain.study.dto.StudyGroupDto;
import com.example.studyroom.repository.ParticipationRepository;
import com.example.studyroom.repository.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyGroupRepository studyGroupRepository;
    private final ParticipationRepository participationRepository;

    public void createStudyGroup(StudyGroupDto dto){
        studyGroupRepository.save(StudyGroupDto.toEntity(dto));
    }
}
