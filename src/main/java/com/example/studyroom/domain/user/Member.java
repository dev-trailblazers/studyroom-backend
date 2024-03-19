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

    @Column(length = 16, updatable = false)
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RoleType role;

    @Column(columnDefinition = "boolean default false")
    private boolean isLocked;

    @Builder
    public Member(String username, String password, String email, String name,
                  LocalDate birth, Education education, RoleType role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.education = education;
        this.role = role;
    }

    public enum RoleType {
        ROLE_USER, ROLE_ADMIN;
    }
}
