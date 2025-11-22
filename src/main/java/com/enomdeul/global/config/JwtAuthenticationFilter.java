package com.enomdeul.global.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = resolveToken(request);

            // 토큰이 있고 유효하다면
            if (token != null && validateToken(token)) {
                // 1. Key 생성 (SecretKey 타입으로 변환)
                SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

                // 2. 토큰 파싱 (0.12.x 문법)
                Jws<Claims> claimsJws = Jwts.parser()
                        .verifyWith(key) // setSigningKey -> verifyWith 로 변경됨
                        .build()
                        .parseSignedClaims(token); // parseClaimsJws -> parseSignedClaims 로 변경됨

                // 3. ID 추출
                String userId = claimsJws.getPayload().getSubject(); // getBody() -> getPayload() 로 변경됨

                // 4. 인증 객체 생성
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 5. 시큐리티 컨텍스트에 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);

                log.info("Security Context에 '{}' 인증 정보를 저장했습니다", userId);
            }
        } catch (Exception e) {
            log.error("유효한 보안 토큰을 찾을 수 없습니다: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}