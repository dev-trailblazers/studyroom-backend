package com.example.studyroom.domain.study.entity;

import com.example.studyroom.domain.AuditingField;
import com.example.studyroom.domain.study.ApplicationStatus;
import com.example.studyroom.domain.user.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
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


    @Builder
    public StudyApplication(Long id, Member member, StudyGroup studyGroup, ApplicationStatus status) {
        this.id = id;
        this.member = member;
        this.studyGroup = studyGroup;
        this.status = status;
    }

}

