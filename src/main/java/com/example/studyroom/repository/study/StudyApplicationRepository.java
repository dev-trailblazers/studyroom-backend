package com.example.studyroom.repository.study;

import com.example.studyroom.domain.study.entity.StudyApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyApplicationRepository extends JpaRepository<StudyApplication, Long> {
}
