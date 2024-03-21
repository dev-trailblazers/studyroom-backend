package com.example.studyroom.repository;

import com.example.studyroom.domain.study.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}
