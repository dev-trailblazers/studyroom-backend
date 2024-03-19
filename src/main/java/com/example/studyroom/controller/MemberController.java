package com.example.studyroom.controller;

import com.example.studyroom.domain.user.dto.MemberJoinDto;
import com.example.studyroom.service.MemberService;
import com.example.studyroom.validation.Username;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/username")
    public boolean validateUsername(@RequestBody @Username String username){
        return memberService.checkDuplicateUsername(username);
    }

    @PostMapping("/join")
    public void joinMember(@RequestBody @Valid MemberJoinDto dto){
        memberService.join(dto);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid LoginDto dto){

    }
}
