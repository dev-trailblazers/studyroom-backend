package com.example.studyroom.repository.study;

import com.example.studyroom.domain.study.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findByStudyGroup_IdAndMember_Id(Long studyId, Long memberId);
}
