package com.example.studyroom.repository.study;

import com.example.studyroom.domain.study.StudyApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyApplicationRepository extends JpaRepository<StudyApplication, Long> {
}
