package com.example.studyroom.config;

import com.example.studyroom.domain.user.Member;
import com.example.studyroom.repository.MemberRepository;
import com.example.studyroom.security.SecurityConfig;
import com.example.studyroom.security.jwt.TokenProvider;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {
    @MockBean TokenProvider tokenProvider;

    @MockBean AuthenticationConfiguration authenticationConfiguration;

    @MockBean private MemberRepository memberRepository;

    @BeforeTestMethod
    public void securitySetUp() {
        given(memberRepository.findByUsername(anyString()))
                .willReturn(Optional.of(
                                Member.builder()
                                        .id(1L)
                                        .username("tester")
                                        .password("asdQWE123!@#")
                                        .name("홍길동")
                                        .birth(LocalDate.of(1999, 11, 23))
                                        .role(Member.RoleType.ROLE_USER)
                                        .build()
                        )
                );
    }
}
