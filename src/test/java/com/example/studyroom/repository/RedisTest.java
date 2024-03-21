package com.example.studyroom.repository;

import com.example.studyroom.domain.auth.EmailVerification;
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
        EmailVerification emailVerification = new EmailVerification(testEmail, "123456");
        emailAuthRedisRepository.save(emailVerification);
    }

    @DisplayName("이메일 인증 - 생성")
    @Test
    void createEmailAuth() {
        //Given
        EmailVerification emailVerification = new EmailVerification(testEmail, "123456");
        //When
        emailAuthRedisRepository.save(emailVerification);
        //Then
        assertThat(emailAuthRedisRepository.count()).isGreaterThan(0);
    }

    @DisplayName("이메일 인증 - 조회")
    @Test
    void findEmailAuth() {
        //Given & When
        Optional<EmailVerification> foundEmailAuth = emailAuthRedisRepository.findById(testEmail);
        //Then
        assertThat(foundEmailAuth).isPresent();
    }

    @DisplayName("이메일 인증 - 수정")
    @Test
    void updateEmailAuth() {
        //Given
        EmailVerification foundEmailVerification = emailAuthRedisRepository.findById(testEmail).get();
        //When
        foundEmailVerification.setStatus(true);
        emailAuthRedisRepository.save(foundEmailVerification);
        //Then
        EmailVerification afterEmailVerification = emailAuthRedisRepository.findById(testEmail).get();
        assertThat(afterEmailVerification.isStatus()).isTrue();
    }

    @DisplayName("이메일 인증 - 삭제")
    @Test
    void deleteEmailAuth() {
        //Given & When
        emailAuthRedisRepository.deleteById(testEmail);
        //Then
        Optional<EmailVerification> emailAuth = emailAuthRedisRepository.findById(testEmail);
        assertThat(emailAuth).isEmpty();
    }
}
