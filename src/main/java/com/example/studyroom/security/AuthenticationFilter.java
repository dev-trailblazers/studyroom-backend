package com.example.studyroom.security;

import com.example.studyroom.security.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    /**
     * 1. 입력받은 username, password를 UsernamePasswordAuthenticationToken으로 변환
     * 2. AuthenticationManager에 전달
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, null)
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();
        String token = tokenProvider.generateToken(customUserDetails);

        response.addHeader("Authorization", "Bearer " + token);
    }
}
