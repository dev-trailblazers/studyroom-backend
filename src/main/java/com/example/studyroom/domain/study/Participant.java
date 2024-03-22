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
public class Participant extends AuditingField {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudyGroup studyGroup;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private ParticipantRole role;


    @Builder
    public Participant(Long id, Member member, StudyGroup studyGroup, ParticipantRole role) {
        this.id = id;
        this.member = member;
        this.studyGroup = studyGroup;
        this.role = role;
    }
}
