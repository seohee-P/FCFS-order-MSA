package com.seohee.fcfsordermsa.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.seohee.fcfsordermsa.domain.user.dto.LoginRequestDto;
import com.seohee.fcfsordermsa.domain.user.dto.UserResponseDto;
import com.seohee.fcfsordermsa.global.response.ApiResponse;
import com.seohee.fcfsordermsa.global.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // 인증
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도");
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공 및 JWT 생성");
        String email = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();

        String token = jwtUtil.createToken(email);
        jwtUtil.addJwtToCookie(token, response);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 객체
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
        UserResponseDto responseDto = new UserResponseDto(((UserDetailsImpl) authResult.getPrincipal()).getUser());
        ApiResponse apiResponse = ApiResponse.ok("Login Successful.",responseDto);
        String jsonString = objectMapper.writeValueAsString(apiResponse);

        // JSON 응답 작성
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.setContentType("application/json");

        // JSON 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ApiResponse apiResponse = ApiResponse.error(
                HttpServletResponse.SC_UNAUTHORIZED, "Failed to login.");
        String jsonString = objectMapper.writeValueAsString(apiResponse);

        // JSON 응답 작성
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();

    }
}