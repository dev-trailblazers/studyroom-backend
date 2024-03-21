package com.example.studyroom.controller;

import com.example.studyroom.domain.auth.EmailVerification;
import com.example.studyroom.domain.auth.EmailVerificationDto;
import com.example.studyroom.service.AuthService;
import com.example.studyroom.service.EmailService;
import com.example.studyroom.validation.MemberEmail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        authService.saveEmailAuth(new EmailVerification(email, code));
    }

    // TODO: 3/21/24 checkVerificationCode로 변경
    @PostMapping("/email")
    public ResponseEntity<Boolean> checkVerificationCodeForEmail(@RequestBody @Valid EmailVerificationDto dto){
        if(authService.checkVerificationCodeForEmail(dto)){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }
    
}
