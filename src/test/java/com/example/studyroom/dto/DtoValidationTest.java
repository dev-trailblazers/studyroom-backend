package com.example.studyroom.dto;

import com.example.studyroom.domain.auth.EmailAuthDto;
import com.example.studyroom.domain.user.dto.MemberJoinDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("요청 데이터 검증")
public class DtoValidationTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @DisplayName("회원가입 - 아이디(실패)")
    @MethodSource("invalidUsernameOfJoinDto")
    @ParameterizedTest(name = "{1}")
    void validate_joinDto_username_fail(String username, String message) {
        //Given
        MemberJoinDto dto = MemberJoinDto.builder()
                .username(username)
                .build();
        //When
        Set<ConstraintViolation<MemberJoinDto>> validEmailViolations = validator.validateProperty(dto, "username");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(1);
    }

    @DisplayName("회원가입 - 아이디(성공)")
    @Test
    void validate_joinDto_username_success() {
        //Given
        MemberJoinDto dto = MemberJoinDto.builder()
                .username("username123")
                .build();
        //When
        Set<ConstraintViolation<MemberJoinDto>> validEmailViolations = validator.validateProperty(dto, "username");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(0);
    }

    @DisplayName("회원가입 - 비밀번호(실패)")
    @MethodSource("invalidPasswordOfJoinDto")
    @ParameterizedTest(name = "{1}")
    void validate_joinDto_password_fail(String password, String message) {
        //Given
        MemberJoinDto dto = MemberJoinDto.builder()
                .password(password)
                .build();
        //When
        Set<ConstraintViolation<MemberJoinDto>> validEmailViolations = validator.validateProperty(dto, "password");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(1);
    }

    @DisplayName("회원가입 - 비밀번호(성공)")
    @Test
    void validate_joinDto_password_success() {
        //Given
        MemberJoinDto dto = MemberJoinDto.builder()
                .password("asdQWE123!@#")
                .build();
        //When
        Set<ConstraintViolation<MemberJoinDto>> validEmailViolations = validator.validateProperty(dto, "password");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(0);
    }

    @DisplayName("회원가입 - 이메일(실패)")
    @Test
    void validate_joinDto_email_fail() {
        //Given
        MemberJoinDto dto = MemberJoinDto.builder()
                .email("aaa123.google.com")
                .build();
        //When
        Set<ConstraintViolation<MemberJoinDto>> validEmailViolations = validator.validateProperty(dto, "email");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(1);
    }

    @DisplayName("회원가입 - 이메일(성공)")
    @Test
    void validate_joinDto_email_success() {
        //Given
        MemberJoinDto dto = MemberJoinDto.builder()
                .email("aaa123@google.com")
                .build();
        //When
        Set<ConstraintViolation<MemberJoinDto>> validEmailViolations = validator.validateProperty(dto, "email");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(0);
    }


    @DisplayName("회원가입 - 이름(실패)")
    @MethodSource("invalidNameOfJoinDto")
    @ParameterizedTest(name = "{1}")
    void validate_joinDto_name_fail(String name, String message) {
        //Given
        MemberJoinDto dto = MemberJoinDto.builder()
                .name(name)
                .build();
        //When
        Set<ConstraintViolation<MemberJoinDto>> validEmailViolations = validator.validateProperty(dto, "name");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(1);
    }

    @DisplayName("회원가입 - 이름(성공)")
    @ValueSource(strings = {"김철", "가나다라마바"})
    @ParameterizedTest
    void validate_joinDto_name_success(String name) {
        //Given
        MemberJoinDto dto = MemberJoinDto.builder()
                .name(name)
                .build();
        //When
        Set<ConstraintViolation<MemberJoinDto>> validEmailViolations = validator.validateProperty(dto, "name");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(0);
    }

    @DisplayName("회원가입 - 생일(실패)")
    @Test
    void validate_joinDto_birth_fail() {
        //Given
        MemberJoinDto dto = MemberJoinDto.builder()
                .birth(LocalDate.now())
                .build();
        //When
        Set<ConstraintViolation<MemberJoinDto>> validEmailViolations = validator.validateProperty(dto, "birth");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(1);
    }

    @DisplayName("회원가입 - 생일(성공)")
    @Test
    void validate_joinDto_birth_success() {
        //Given
        LocalDate localDate = LocalDate.now();
        MemberJoinDto dto = MemberJoinDto.builder()
                .birth(localDate.minusDays(1))
                .build();
        //When
        Set<ConstraintViolation<MemberJoinDto>> validEmailViolations = validator.validateProperty(dto, "birth");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(0);
    }

    @DisplayName("이메일 인증 - 이메일(실패)")
    @Test
    void validate_emailAuth_email_fail() {
        //Given
        EmailAuthDto dto = EmailAuthDto.builder()
                .email("test.email.com")
                .build();
        //When
        Set<ConstraintViolation<EmailAuthDto>> validEmailViolations = validator.validateProperty(dto, "email");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(1);
    }

    @DisplayName("이메일 인증 - 이메일(성공)")
    @Test
    void validate_emailAuth_email_success() {
        //Given
        EmailAuthDto dto = EmailAuthDto.builder()
                .email("test@email.com")
                .build();
        //When
        Set<ConstraintViolation<EmailAuthDto>> validEmailViolations = validator.validateProperty(dto, "email");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(0);
    }


    @DisplayName("이메일 인증 - 인증코드(실패)")
    @ValueSource(strings = {"12345", "1234567"})
    @ParameterizedTest
    void validate_emailAuth_codeLength_fail(String code) {
        //Given
        EmailAuthDto dto = EmailAuthDto.builder()
                .code(code)
                .build();
        //When
        Set<ConstraintViolation<EmailAuthDto>> validEmailViolations = validator.validateProperty(dto, "code");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(1);
    }

    @DisplayName("이메일 인증 - 인증코드(성공)")
    @Test
    void validate_emailAuth_codeLength_success() {
        //Given
        EmailAuthDto dto = EmailAuthDto.builder()
                .code("123456")
                .build();
        //When
        Set<ConstraintViolation<EmailAuthDto>> validEmailViolations = validator.validateProperty(dto, "code");
        //Then
        assertThat(validEmailViolations.size()).isEqualTo(0);
    }


    static Stream<Arguments> invalidUsernameOfJoinDto(){
        return Stream.of(
                Arguments.of("a1", "3자리 미만"),
                Arguments.of("aaaabbbb123456789", "16자리 초과"),
                Arguments.of("aaaa", "숫자 미포함"),
                Arguments.of("1111", "영어 소문자 미포함"),
                Arguments.of("asd123!@#", "영소문자 + 숫자 이외의 조합(특수문자)"),
                Arguments.of("aaAA123", "영소문자 + 숫자 이외의 조합(대문자)")
        );
    }

    static Stream<Arguments> invalidPasswordOfJoinDto(){
        return Stream.of(
                Arguments.of("asd123!", "8자리 미만"),
                Arguments.of("aaaabbbb12345!@#$", "16자리 초과"),
                Arguments.of("asdQWE!@#", "숫자 미포함"),
                Arguments.of("asdQWE123", "특수문자 미포함"),
                Arguments.of("ASD123!@#", "소문자 미포함"),
                Arguments.of("asd123!@#", "대문자 미포함")
        );
    }

    static Stream<Arguments> invalidNameOfJoinDto(){
        return Stream.of(
                Arguments.of("김", "2자리 미만"),
                Arguments.of("가나다라마바사", "6자리 초과"),
                Arguments.of("KimChulSoo", "한글 이외 문자 포함(영어)"),
                Arguments.of("김철수!", "한글 이외 문자 포함(특수문자)")
        );
    }
}
