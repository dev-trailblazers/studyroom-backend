package com.example.studyroom.controller;

import com.example.studyroom.domain.study.dto.StudyGroupDto;
import com.example.studyroom.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/study")
@RestController
public class StudyController {
    private final StudyService studyService;

    @PostMapping
    public void studySave(@RequestBody @Valid StudyGroupDto dto){
        studyService.createStudyGroup(dto);
    }
}
