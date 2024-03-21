package com.example.studyroom.controller;

import com.example.studyroom.domain.user.dto.MemberJoinDto;
import com.example.studyroom.service.MemberService;
import com.example.studyroom.validation.Username;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/username")
    public ResponseEntity<Boolean> validateUsername(@RequestBody @Username String username){
        if(memberService.checkDuplicateUsername(username)){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }

    @PostMapping("/join")
    public void joinMember(@RequestBody @Valid MemberJoinDto dto){
        memberService.join(dto);
    }
}
