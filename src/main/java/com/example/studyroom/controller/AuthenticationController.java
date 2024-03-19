package com.example.studyroom.controller;

import com.example.studyroom.domain.auth.EmailAuth;
import com.example.studyroom.domain.auth.EmailAuthDto;
import com.example.studyroom.service.AuthService;
import com.example.studyroom.service.EmailService;
import com.example.studyroom.validation.MemberEmail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/new/email")
    public void requestAuthCodeForEmail(@RequestBody @MemberEmail String email){
        String code = emailService.sendAuthCode(email);
        authService.saveEmailAuth(new EmailAuth(email, code));
    }

    @PostMapping("/email")
    public boolean checkAuthCodeForEmail(@RequestBody @Valid EmailAuthDto dto){
        return authService.checkEmailAuth(dto);
    }

}
