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
    @Column(columnDefinition = "varchar(10) not null default '대기'")
    private ApplicationStatus status;
}

