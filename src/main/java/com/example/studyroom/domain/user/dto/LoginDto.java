package com.example.studyroom.domain.user.dto;

import com.example.studyroom.validation.Password;
import com.example.studyroom.validation.Username;

public record LoginDto(
        @Username String username,
        @Password String password
) {
}
