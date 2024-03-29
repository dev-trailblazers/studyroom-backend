package com.example.studyroom.service;

import com.example.studyroom.domain.study.ApplicationStatus;
import com.example.studyroom.domain.study.entity.Participant;
import com.example.studyroom.domain.study.ParticipantRole;
import com.example.studyroom.domain.study.entity.StudyApplication;
import com.example.studyroom.domain.study.entity.StudyGroup;
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

    public StudyGroupDto createStudyGroup(RequestStudyGroupDto dto, Long memberId) {
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
        Participant participant = participationRepository.findByStudyGroup_IdAndMember_Id(studyId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("스터디 참가자가 아닙니다."));
        if (participant.getRole() == ParticipantRole.LEADER) {
            StudyGroup studyGroup = studyGroupRepository.findById(studyId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디 입니다."));
            studyGroup.setRecruit(true);
        }
    }

    public void applyForStudy(Long studyId, CustomUserDetails user) {
        StudyGroup studyGroup = studyGroupRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디입니다."));

        StudyApplication studyApplication = StudyApplication.builder()
                .member(user.getMember())
                .studyGroup(studyGroup)
                .build();
        studyApplicationRepository.save(studyApplication);
    }

    public void approvalForStudyApplication(Long applicationId, CustomUserDetails user, boolean status) {
        StudyApplication studyApplication = studyApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("신청 정보가 존재하지 않습니다."));

        Participant participant = participationRepository
                .findByStudyGroup_IdAndMember_Id(studyApplication.getStudyGroup().getId(), user.getId())
                .orElseThrow(() -> new IllegalArgumentException("스터디 참가자가 아닙니다."));

        if(participant.getRole().equals(ParticipantRole.LEADER)){
            studyApplication.setStatus(ApplicationStatus.getStatus(status));
        }
        else{
            throw new IllegalArgumentException("방장만 스터디 가입 신청을 받을 수 있습니다.");
        }
    }
}
