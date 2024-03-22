package com.example.studyroom.service;

import com.example.studyroom.domain.study.Participant;
import com.example.studyroom.domain.study.ParticipantRole;
import com.example.studyroom.domain.study.StudyGroup;
import com.example.studyroom.domain.study.dto.RequestStudyGroupDto;
import com.example.studyroom.domain.study.dto.StudyGroupDto;
import com.example.studyroom.domain.user.Member;
import com.example.studyroom.repository.study.StudyApplicationRepository;
import com.example.studyroom.repository.study.ParticipationRepository;
import com.example.studyroom.repository.study.StudyGroupRepository;
import com.example.studyroom.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class StudyService {
    private final StudyGroupRepository studyGroupRepository;
    private final ParticipationRepository participationRepository;
    private final StudyApplicationRepository studyApplicationRepository;

    public StudyGroupDto createStudyGroup(RequestStudyGroupDto dto, Long memberId){
        StudyGroup studyGroup = studyGroupRepository.save(RequestStudyGroupDto.toEntity(dto));

        Participant participant = Participant.builder()
                .member(Member.builder()
                        .id(memberId)
                        .build()
                )
                .studyGroup(studyGroup)
                .role(ParticipantRole.LEADER)
                .build();
        participationRepository.save(participant);

        return StudyGroupDto.fromEntity(studyGroup);
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
