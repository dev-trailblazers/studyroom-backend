package com.example.studyroom.repository.study;

import com.example.studyroom.domain.study.StudyParticipation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyParticipationRepository extends JpaRepository<StudyParticipation, Long> {
}
