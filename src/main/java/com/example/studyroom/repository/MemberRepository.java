package com.example.studyroom.repository;

import com.example.studyroom.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsMemberByUsername(String username);
}
