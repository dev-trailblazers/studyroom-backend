package com.example.studyroom.service;

import com.example.studyroom.domain.auth.EmailVerification;
import com.example.studyroom.domain.user.Member;
import com.example.studyroom.domain.user.dto.MemberJoinDto;
import com.example.studyroom.repository.EmailAuthRedisRepository;
import com.example.studyroom.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final EmailAuthRedisRepository emailAuthRedisRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean checkDuplicateUsername(String username){
        return memberRepository.existsMemberByUsername(username);
    }

    public void join(MemberJoinDto dto){
        if(checkDuplicateUsername(dto.username())){
            throw new IllegalArgumentException("아이디는 중복될 수 없습니다.");
        }
        Optional<EmailVerification> auth = emailAuthRedisRepository.findById(dto.email());
        if(auth.isEmpty() || !auth.get().isStatus()){
            throw new IllegalArgumentException("이메일 인증 후 시도해주세요.");
        }

        Member entity = dto.toEntity();
        String encodedPassword = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);

        memberRepository.save(entity);
    }
}
