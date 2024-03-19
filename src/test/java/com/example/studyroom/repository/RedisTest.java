package com.example.studyroom.repository;

import com.example.studyroom.domain.auth.EmailAuth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Redis Repository 테스트")
@ActiveProfiles("test")
@DataRedisTest
public class RedisTest {
    private EmailAuthRedisRepository emailAuthRedisRepository;
    private String testEmail = "test@email.com";

    public RedisTest(@Autowired EmailAuthRedisRepository emailAuthRedisRepository) {
        this.emailAuthRedisRepository = emailAuthRedisRepository;
    }

    @BeforeEach
    void setUp() {
        EmailAuth emailAuth = new EmailAuth(testEmail, "123456");
        emailAuthRedisRepository.save(emailAuth);
    }

    @DisplayName("이메일 인증 - 생성")
    @Test
    void createEmailAuth() {
        //Given
        EmailAuth emailAuth = new EmailAuth(testEmail, "123456");
        //When
        emailAuthRedisRepository.save(emailAuth);
        //Then
        assertThat(emailAuthRedisRepository.count()).isGreaterThan(0);
    }

    @DisplayName("이메일 인증 - 조회")
    @Test
    void findEmailAuth() {
        //Given & When
        Optional<EmailAuth> foundEmailAuth = emailAuthRedisRepository.findById(testEmail);
        //Then
        assertThat(foundEmailAuth).isPresent();
    }

    @DisplayName("이메일 인증 - 수정")
    @Test
    void updateEmailAuth() {
        //Given
        EmailAuth foundEmailAuth = emailAuthRedisRepository.findById(testEmail).get();
        //When
        foundEmailAuth.setStatus(true);
        emailAuthRedisRepository.save(foundEmailAuth);
        //Then
        EmailAuth afterEmailAuth = emailAuthRedisRepository.findById(testEmail).get();
        assertThat(afterEmailAuth.isStatus()).isTrue();
    }

    @DisplayName("이메일 인증 - 삭제")
    @Test
    void deleteEmailAuth() {
        //Given & When
        emailAuthRedisRepository.deleteById(testEmail);
        //Then
        Optional<EmailAuth> emailAuth = emailAuthRedisRepository.findById(testEmail);
        assertThat(emailAuth).isEmpty();
    }
}
