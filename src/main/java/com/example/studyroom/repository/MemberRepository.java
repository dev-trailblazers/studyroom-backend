package com.example.studyroom.repository;

import com.example.studyroom.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsMemberByUsername(String username);

    Optional<Member> findByUsername(String username);
}
