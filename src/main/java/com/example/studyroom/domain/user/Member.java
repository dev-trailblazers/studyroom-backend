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

    @Column(length = 16, nullable = false, updatable = false)
    private String username;

    @Setter
    @Column(length = 68, nullable = false)
    private String password;

    @Column(length = 30, updatable = false)
    private String email;

    @Column(length = 18, nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RoleType role;

    @Setter
    @Column(length = 20)
    @Enumerated(value = EnumType.STRING)
    private Education education;

    @Column(length = 2083)
    private String profile_image;

    @Column(columnDefinition = "boolean default false")
    private boolean isLocked;


    @Builder
    public Member(Long id, String username, String password, String email, String name,
                  LocalDate birth, Education education, RoleType role,
                  Long modifiedBy) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.education = education;
        this.role = role;
        this.modifiedBy = modifiedBy;
    }

    public static Member of(String username, String password, String  email,
                            String name, LocalDate birth,  RoleType role){
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .birth(birth)
                .role(role)
                .build();
    }

    public static Member of(String username, String password, String name,
                            LocalDate birth,  RoleType role){
        return Member.builder()
                .username(username)
                .password(password)
                .name(name)
                .birth(birth)
                .role(role)
                .build();
    }

    public static Member of(String username, String password, String name,
                            LocalDate birth,  RoleType role, Long modified_by) {
        return Member.builder()
                .username(username)
                .password(password)
                .name(name)
                .birth(birth)
                .role(role)
                .modifiedBy(modified_by)
                .build();
    }
}
