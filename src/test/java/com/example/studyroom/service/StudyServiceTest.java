package com.example.studyroom.service;

import com.example.studyroom.domain.study.dto.RequestStudyGroupDto;
import com.example.studyroom.domain.study.dto.StudyGroupDto;
import com.example.studyroom.repository.study.ParticipationRepository;
import com.example.studyroom.repository.study.StudyGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;


@DisplayName("비즈니스 로직 - 스터디")
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
    @InjectMocks StudyService studyService;
    @Mock StudyGroupRepository studyGroupRepository;
    @Mock
    ParticipationRepository participationRepository;


    @DisplayName("스터디 그룹 생성 - 성공")
    @Test
    void studyGroup_create_success() {
        //Given
        RequestStudyGroupDto dto = RequestStudyGroupDto.of(
                "스터디그룹1",
                "영어",
                LocalDate.of(2024, 3, 10),
                LocalDate.of(2025, 3, 10),
                (byte) 5,
                false
        );
        //When
        studyService.createStudyGroup(dto, 1L);
        then(studyGroupRepository).should().save(any());
        then(participationRepository).should().save(any());
    }
}