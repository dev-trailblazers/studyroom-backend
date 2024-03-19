package com.example.studyroom.service;

import com.example.studyroom.domain.auth.EmailAuth;
import com.example.studyroom.domain.auth.EmailAuthDto;
import com.example.studyroom.repository.EmailAuthRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final EmailAuthRedisRepository emailAuthRedisRepository;

    public void saveEmailAuth(EmailAuth emailAuth){
        emailAuthRedisRepository.save(emailAuth);
    }

    public boolean checkEmailAuth(EmailAuthDto dto) {
        EmailAuth auth = emailAuthRedisRepository.findById(dto.email())
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
