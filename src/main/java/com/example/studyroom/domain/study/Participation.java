package com.example.studyroom.domain.study;

import com.example.studyroom.domain.AuditingField;
import com.example.studyroom.domain.user.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Participation extends AuditingField {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudyGroup studyGroup;


    @Builder
    public Participation(Long id, Member member, StudyGroup studyGroup) {
        this.id = id;
        this.member = member;
        this.studyGroup = studyGroup;
    }
}
