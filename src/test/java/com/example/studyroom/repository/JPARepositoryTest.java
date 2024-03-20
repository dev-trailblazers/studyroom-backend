package com.example.studyroom.repository;


import com.example.studyroom.domain.user.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@DisplayName("JPA 테스트")
@ActiveProfiles("test")
@DataJpaTest
public class JPARepositoryTest {
    private final MemberRepository memberRepository;

    public JPARepositoryTest(@Autowired MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @DisplayName("회원 - 생성")
    @Test
    void saveMember() {
        //Given
        Member member = Member.of(
                        "tester",
                        "Password123@",
                        "홍길동",
                        LocalDate.of(1999,11,23),
                        Member.RoleType.ROLE_USER
                );
        //When
        memberRepository.save(member);
        //Then
        boolean result = memberRepository.existsMemberByUsername("tester");
        assertThat(result).isTrue();
    }

    @DisplayName("회원 - 조회")
    @Test
    void findMember() {
        //Given & When
        Optional<Member> member = memberRepository.findById(1L);
        //Then
        assertThat(member).isPresent();
    }

    @DisplayName("회원 - 수정")
    @Test
    void updateMember() {
        //Given
        Member member = memberRepository.findById(1L).get();
        //When
        member.setEducation(Member.Education.대학원생);
        //Then
        memberRepository.saveAndFlush(member);
        Member afterMember = memberRepository.findById(1L).get();
        assertThat(afterMember.getEducation()).isEqualTo(Member.Education.대학원생);
    }

    @DisplayName("회원 - 삭제")
    @Test
    void deleteMember() {
        //Given & When
        memberRepository.deleteById(1L);
        Optional<Member> member = memberRepository.findById(1L);
        //Then
        assertThat(member).isEmpty();
    }


    @EnableJpaAuditing
    @TestConfiguration
    public static class TestJpaConfig {
        @Bean
        public AuditorAware<Long> auditorAware() {
            return () -> Optional.ofNullable(1L);
        }
    }
}
