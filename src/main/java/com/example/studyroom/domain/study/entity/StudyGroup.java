package com.example.studyroom.domain.study.entity;

import com.example.studyroom.domain.AuditingField;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class StudyGroup extends AuditingField {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60,nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private byte headcount;

    @Setter
    @Column(columnDefinition = "boolean not null default false")
    private boolean isRecruit;


    @Builder
    public StudyGroup(Long id, String name, String type, LocalDate startDate,
                      LocalDate endDate, byte headcount, boolean isRecruit) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.headcount = headcount;
        this.isRecruit = isRecruit;
    }
}
