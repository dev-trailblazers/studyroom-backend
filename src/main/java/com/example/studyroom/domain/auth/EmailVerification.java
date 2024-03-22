package com.example.studyroom.domain.auth;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@RedisHash(value = "email", timeToLive = 60*5)
public class EmailVerification {
    @Id
    private String email;
    private String code;
    @Setter
    private boolean status = false;

    public EmailVerification(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
