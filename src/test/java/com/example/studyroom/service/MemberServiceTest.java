package com.example.studyroom.service;


import com.example.studyroom.domain.auth.EmailAuth;
import com.example.studyroom.domain.user.dto.MemberJoinDto;
import com.example.studyroom.repository.EmailAuthRedisRepository;
import com.example.studyroom.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 유저")
@ExtendWith(MockitoExtension.class) //@ExtendWith = 단위 테스트에 사용될 확장 기능을 선언, MockitoExtension = 모의 객체를 포함한 extension
class MemberServiceTest {

    @InjectMocks MemberService memberService;
    @Mock MemberRepository memberRepository;
    @Mock EmailAuthRedisRepository emailAuthRedisRepository;


    @DisplayName("아이디 중복 검사 - 실패")
    @Test
    void username_duplication_check_fail() {
        given(memberRepository.existsMemberByUsername(anyString())).willReturn(true);
        boolean result = memberService.checkDuplicateUsername("username123");
        then(memberRepository).should().existsMemberByUsername(anyString());
        assertThat(result).isTrue();
    }

    @DisplayName("아이디 중복 검사 - 성공")
    @Test
    void username_duplication_check_success() {
        given(memberRepository.existsMemberByUsername(anyString())).willReturn(false);
        boolean result = memberService.checkDuplicateUsername("username123");
        then(memberRepository).should().existsMemberByUsername(anyString());
        assertThat(result).isFalse();
    }

    @DisplayName("회원가입 - 아이디 중복(실패)")
    @Test
    void join_duplicate_username() {
        given(memberRepository.existsMemberByUsername(anyString())).willReturn(true);
        assertThatThrownBy(() -> memberService.join(
                MemberJoinDto.builder()
                        .username("tester1")
                        .build())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("회원가입 - 이메일 인증요청 전(실패)")
    @Test
    void join_not_request_emailAuth() {
        given(emailAuthRedisRepository.findById(anyString())).willReturn(Optional.empty());
        assertThatThrownBy(() -> memberService.join(
                MemberJoinDto.builder()
                        .username("tester1")
                        .email("test@email.com")
                        .build())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("회원가입 - 이메일 인증 미완료(실패)")
    @Test
    void join_unAuthorized_emailCode() {
        given(emailAuthRedisRepository.findById(anyString())).willReturn(
                Optional.of(new EmailAuth("test@email.com", "123456"))
        );
        assertThatThrownBy(() -> memberService.join(
                MemberJoinDto.builder()
                        .username("tester1")
                        .email("test@email.com")
                        .build())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("회원가입 - 성공")
    @Test
    void join_success() {
        EmailAuth emailAuth = new EmailAuth("test@email.com", "123456");
        emailAuth.setStatus(true);
        given(emailAuthRedisRepository.findById(anyString())).willReturn(Optional.of(emailAuth));
        memberService.join(
                MemberJoinDto.builder()
                        .username("tester1")
                        .email("test@email.com")
                        .build()
        );
        then(memberRepository).should().save(any());
    }
}