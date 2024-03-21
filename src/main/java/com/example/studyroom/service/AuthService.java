package com.example.studyroom.service;

import com.example.studyroom.domain.auth.EmailVerification;
import com.example.studyroom.domain.auth.EmailVerificationDto;
import com.example.studyroom.repository.EmailAuthRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final EmailAuthRedisRepository emailAuthRedisRepository;

    public void saveEmailAuth(EmailVerification emailVerification){
        emailAuthRedisRepository.save(emailVerification);
    }

    public boolean checkVerificationCodeForEmail(EmailVerificationDto dto) {
        EmailVerification auth = emailAuthRedisRepository.findById(dto.email())
                .orElseThrow(() -> new IllegalArgumentException("인증번호를 발송한 후 시도해주세요."));

        String code = auth.getCode();
        if(code.equals(dto.code())){
            auth.setStatus(true);
            emailAuthRedisRepository.save(auth);
            return true;
        }
        return false;
    }
}
