package com.example.studyroom.domain.study.entity;

import com.example.studyroom.domain.AuditingField;
import com.example.studyroom.domain.study.ApplicationStatus;
import com.example.studyroom.domain.user.Member;
import jakarta.persistence.*;

@Entity
public class StudyApplication extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudyGroup studyGroup;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private ApplicationStatus status = ApplicationStatus.대기;
}

