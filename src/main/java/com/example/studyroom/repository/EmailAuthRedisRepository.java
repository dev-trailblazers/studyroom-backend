package com.example.studyroom.repository;

import com.example.studyroom.domain.auth.EmailVerification;
import org.springframework.data.repository.CrudRepository;

public interface EmailAuthRedisRepository extends CrudRepository<EmailVerification, String> {
}
