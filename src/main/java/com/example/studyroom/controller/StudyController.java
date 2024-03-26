package com.example.studyroom.controller;

import com.example.studyroom.domain.study.dto.RequestStudyGroupDto;
import com.example.studyroom.domain.study.dto.StudyGroupDto;
import com.example.studyroom.security.CustomUserDetails;
import com.example.studyroom.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/study")
@RestController
public class StudyController {
    private final StudyService studyService;

    @PostMapping("/new")
    public ResponseEntity<StudyGroupDto> studySave(@RequestBody @Valid RequestStudyGroupDto dto, @AuthenticationPrincipal CustomUserDetails user){
        StudyGroupDto studyGroupDto = studyService.createStudyGroup(dto, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(studyGroupDto);
    }

    @PostMapping("/recruitment/{studyId}")
    public void studyRecruit(@PathVariable Long studyId, @AuthenticationPrincipal CustomUserDetails user){
        studyService.recruitStudy(studyId, user);
    }
}
