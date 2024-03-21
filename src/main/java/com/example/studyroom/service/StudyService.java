package com.example.studyroom.service;

import com.example.studyroom.domain.study.Participant;
import com.example.studyroom.domain.study.ParticipantRole;
import com.example.studyroom.domain.study.StudyGroup;
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

    public void recruitStudy(Long studyId, CustomUserDetails user) {
        Participant participant = participationRepository.findByMember_Id(user.getId());
        if(participant.getRole() == ParticipantRole.LEADER){
            StudyGroup studyGroup = studyGroupRepository.findById(studyId)
                    .orElseThrow(() -> new IllegalArgumentException());
            studyGroup.setRecruit(true);
        }
    }
}
