package com.example.studyroom.controller;

import com.example.studyroom.domain.study.dto.StudyGroupDto;
import com.example.studyroom.security.CustomUserDetails;
import com.example.studyroom.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/study")
@RestController
public class StudyController {
    private final StudyService studyService;

    @PostMapping("/new")
    public void studySave(@RequestBody @Valid StudyGroupDto dto,  @AuthenticationPrincipal CustomUserDetails user){
        studyService.createStudyGroup(dto, user.getId());
    }

    @PostMapping("/recruitment/{studyId}")
    public void studyRecruit(@PathVariable Long studyId, @AuthenticationPrincipal CustomUserDetails user){
        studyService.recruitStudy(studyId, user);
    }
}
