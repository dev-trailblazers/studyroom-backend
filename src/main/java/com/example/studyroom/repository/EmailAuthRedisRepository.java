package com.example.studyroom.repository;

import com.example.studyroom.domain.auth.EmailAuth;
import org.springframework.data.repository.CrudRepository;

public interface EmailAuthRedisRepository extends CrudRepository<EmailAuth, String> {
}
