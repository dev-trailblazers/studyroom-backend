package com.example.studyroom.controller;

import com.example.studyroom.domain.user.MemberJoinDto;
import com.example.studyroom.service.MemberService;
import jakarta.validation.constraints.Pattern;
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
    public boolean validateUsername(@RequestBody @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])[a-z0-9]{3,16}$") String username){
        return memberService.checkDuplicateUsername(username);
    }

    @PostMapping("/join")
    public void joinMember(@RequestBody MemberJoinDto dto){
        memberService.join(dto);
    }
}
