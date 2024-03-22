package com.example.studyroom.controller;


import com.example.studyroom.config.TestSecurityConfig;
import com.example.studyroom.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("API - 멤버 컨트롤러")
@Import(TestSecurityConfig.class)
@WebMvcTest(MemberController.class)
class MemberControllerTest {
    private final MockMvc mvc;
    private final ObjectMapper mapper;
    @MockBean MemberService memberService;

    public MemberControllerTest(@Autowired MockMvc mvc, @Autowired ObjectMapper mapper) {
        this.mvc = mvc;
        this.mapper = mapper;
    }

    @DisplayName("[POST] 아이디 중복 검사 - 성공")
    @Test
    void duplicateCheck_username_200() throws Exception {
        given(memberService.checkDuplicateUsername(anyString())).willReturn(false);
        mvc.perform(post("/member/username")
                        .content("tester1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("[POST] 아이디 중복 검사 - 실패")
    @Test
    void duplicateCheck_username_400() throws Exception {
        given(memberService.checkDuplicateUsername(anyString())).willReturn(true);
        mvc.perform(post("/member/username")
                        .content("tester1"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}