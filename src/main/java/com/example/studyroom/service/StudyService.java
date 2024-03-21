package com.example.studyroom.service;

import com.example.studyroom.domain.study.dto.StudyGroupDto;
import com.example.studyroom.repository.study.StudyApplicationRepository;
import com.example.studyroom.repository.study.ParticipationRepository;
import com.example.studyroom.repository.study.StudyGroupRepository;
import com.example.studyroom.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyGroupRepository studyGroupRepository;
    private final ParticipationRepository participationRepository;
    private final StudyApplicationRepository studyApplicationRepository;

    public void createStudyGroup(StudyGroupDto dto){
        studyGroupRepository.save(StudyGroupDto.toEntity(dto));
    }

}
