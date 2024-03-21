package com.example.studyroom.service;

import com.example.studyroom.domain.study.dto.StudyGroupDto;
import com.example.studyroom.repository.ParticipationRepository;
import com.example.studyroom.repository.StudyGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


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
        StudyGroupDto dto = new StudyGroupDto("스터디그룹1", "영어");
        //When
        studyService.createStudyGroup(dto);
    }
}