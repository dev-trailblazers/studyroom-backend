package com.example.studyroom.domain.study;

import com.example.studyroom.domain.AuditingField;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


    @Builder
    public StudyGroup(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
