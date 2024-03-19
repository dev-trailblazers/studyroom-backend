package com.example.studyroom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<Long> auditorAware(){
        return () -> Optional.of(1L);   // TODO: 3/17/24 시큐리티 인증 후 구현
    }
}
