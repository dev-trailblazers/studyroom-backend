package com.example.studyroom.repository.study;

import com.example.studyroom.domain.study.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participant, Long> {
    Participant findByStudyGroup_IdAndMember_Id(Long studyId, Long memberId);
}
