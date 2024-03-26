package com.example.studyroom.repository.study;

import com.example.studyroom.domain.study.entity.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {
}
