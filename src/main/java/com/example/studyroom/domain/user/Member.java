package com.example.studyroom.domain.user;


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
public class Member extends AuditingField {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 16, updatable = false)
    private String username;

    @Setter
    @Column(length = 60)
    private String password;

    @Column(length = 30, nullable = false, updatable = false)
    private String email;

    @Column(length = 18, nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private LocalDate birth;

    @Setter
    @Column(length = 20, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Education education;

    @Column(length = 2083)
    private String profile_image;

    @Builder
    public Member(String username, String password, String email, String name, LocalDate birth, Education education) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.education = education;
    }
}