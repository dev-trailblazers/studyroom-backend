package com.example.studyroom.security;

import com.example.studyroom.domain.user.Member;
import com.example.studyroom.domain.user.RoleType;
import com.example.studyroom.repository.MemberRepository;
import com.example.studyroom.security.jwt.TokenAuthenticationFilter;
import com.example.studyroom.security.jwt.TokenProvider;
import com.example.studyroom.security.oauth.KakaoOAuth2Response;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.UUID;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService
    ) throws Exception {
        http
                .csrf(auth -> auth.disable())
                .formLogin(auth -> auth.disable())
                .httpBasic(auth -> auth.disable())
                .oauth2Login(auth -> auth
                        .userInfoEndpoint(user -> user.userService(oAuth2UserService))
                )
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/auth/**", "/member/username", "/member/join").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new TokenAuthenticationFilter(tokenProvider), AuthenticationFilter.class)
                .addFilterAt(
                        new AuthenticationFilter(
                                authenticationManager(authenticationConfiguration), tokenProvider
                        ),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(MemberRepository memberRepository) {
        return username -> memberRepository.findByUsername(username)
                .map(member -> new CustomUserDetails(member))
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저입니다."));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(MemberRepository memberRepository,
                                                                              PasswordEncoder passwordEncoder) {
        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return userRequest -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            KakaoOAuth2Response kakaoResponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            String providerId = String.valueOf(kakaoResponse.id());
            String username = registrationId + "_" + providerId;
            String dummyPassword = passwordEncoder.encode(UUID.randomUUID().toString());

            return memberRepository.findByUsername(username)
                    .map(CustomUserDetails::new)
                    .orElseGet(() -> new CustomUserDetails(
                                    memberRepository.save(
                                            Member.of(
                                                    username,
                                                    dummyPassword,
                                                    kakaoResponse.kakaoAccount().name(),
                                                    kakaoResponse.kakaoAccount().birth(),
                                                    kakaoResponse.kakaoAccount().gender(),
                                                    RoleType.ROLE_USER,
                                                    1L
                                            )
                                    )
                            )
                    );
        };
    }
}