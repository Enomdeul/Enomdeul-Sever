package com.enomdeul.global.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long accessTokenValidity;
    private final long refreshTokenValidity;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.accessTokenValidity = 1000L * 60 * 60 * 3; // 3시간
        this.refreshTokenValidity = 1000L * 60 * 60 * 24 * 7; // 7일
    }

    // 액세스 토큰 생성
    public String createAccessToken(Long userId) {
        return createToken(userId, accessTokenValidity);
    }

    // 리프레시 토큰 생성
    public String createRefreshToken(Long userId) {
        return createToken(userId, refreshTokenValidity);
    }

    private String createToken(Long userId, long validity) {
        Date now = new Date();
        Date validityDate = new Date(now.getTime() + validity);

        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // 토큰 안에 유저 ID 저장
                .setIssuedAt(now) // 발급 시간
                .setExpiration(validityDate) // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 서명
                .compact();
    }
}