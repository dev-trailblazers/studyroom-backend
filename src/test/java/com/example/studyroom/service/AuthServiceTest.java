package com.example.studyroom.service;

import com.example.studyroom.domain.auth.EmailAuth;
import com.example.studyroom.domain.auth.EmailAuthDto;
import com.example.studyroom.repository.EmailAuthRedisRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @InjectMocks AuthService authService;
    @Mock EmailAuthRedisRepository emailAuthRedisRepository;


    @DisplayName("이메일 인증번호 확인 - 인증 요청한 이메일이 없는 경우")
    @Test
    void check_authCode_null() {
        given(emailAuthRedisRepository.findById(anyString())).willReturn(Optional.empty());
        assertThatThrownBy(() -> authService.checkEmailAuth(new EmailAuthDto("test@email.com", "123456")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이메일 인증번호 확인 - 인증 코드가 다를 경우")
    @Test
    void check_authCode_mismatch() {
        given(emailAuthRedisRepository.findById(anyString())).willReturn(
                Optional.of(new EmailAuth("test@email.com", "654321"))
        );
        boolean result = authService.checkEmailAuth(new EmailAuthDto("test@email.com", "123456"));
        assertThat(result).isFalse();
    }

    @DisplayName("이메일 인증번호 확인 - 성공")
    @Test
    void check_authCode_success() {
        given(emailAuthRedisRepository.findById(anyString())).willReturn(
                Optional.of(new EmailAuth("test@email.com", "123456"))
        );
        boolean result = authService.checkEmailAuth(new EmailAuthDto("test@email.com", "123456"));
        assertThat(result).isTrue();
    }
}