package com.example.studyroom.security.jwt;

import com.example.studyroom.domain.user.Member;
import com.example.studyroom.domain.user.RoleType;
import com.example.studyroom.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);    //체이닝된 필터를 doFilter를 호출해서 다음 필터에 전달 후 종료시킨다.
            return;
        }

        String token = authorization.split(" ")[1];

        if (tokenProvider.isExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = tokenProvider.getUsername(token);
        String role = tokenProvider.getRole(token);

        Member member = Member.builder()
                .username(username)
                .password("tmp")
                .role(RoleType.valueOf(role))
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
