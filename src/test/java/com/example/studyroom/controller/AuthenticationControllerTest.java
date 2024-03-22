package com.example.studyroom.controller;

import com.example.studyroom.config.TestSecurityConfig;
import com.example.studyroom.domain.auth.EmailVerificationDto;
import com.example.studyroom.service.AuthService;
import com.example.studyroom.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("API - 인증 컨트롤러")
@Import(TestSecurityConfig.class)
@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {
    private final MockMvc mvc;
    private final ObjectMapper mapper;
    @MockBean AuthService authService;
    @MockBean EmailService emailService;


    public AuthenticationControllerTest(@Autowired MockMvc mvc, @Autowired ObjectMapper mapper) {
        this.mvc = mvc;
        this.mapper = mapper;
    }


    @DisplayName("이메일 인증 - 성공")
    @Test
    void check_emailAuth_success() throws Exception {
        given(authService.checkVerificationCodeForEmail(any())).willReturn(true);
        mvc.perform(post("/auth/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new EmailVerificationDto("test@email.com", "123456"))))
                .andExpect(status().isOk());
    }

    @DisplayName("이메일 인증 - 실패")
    @Test
    void check_emailAuth_fail() throws Exception {
        given(authService.checkVerificationCodeForEmail(any())).willReturn(false);
        mvc.perform(post("/auth/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new EmailVerificationDto("test@email.com", "123456"))))
                .andExpect(status().isBadRequest());
    }
}