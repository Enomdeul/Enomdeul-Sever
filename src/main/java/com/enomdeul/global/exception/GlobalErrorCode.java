package com.enomdeul.global.exception;

import com.enomdeul.global.common.response.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    // 1. 서버 내부 오류 (500)
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GLOBAL_001", "서버 내부 오류가 발생했습니다."),

    // 2. 토큰 관련 오류
    TOKEN_INVALID_ERROR(HttpStatus.UNAUTHORIZED, "AUTH_001", "유효하지 않는 토큰입니다."),
    TOKEN_EXPIRED_ERROR(HttpStatus.UNAUTHORIZED, "AUTH_002", "만료된 토큰입니다."),

    // 3. 기타 공통 오류
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "GLOBAL_003", "지원하지 않는 HTTP 메서드입니다."),

    // 4. 유저 관련 오류
    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "USER_001", "사용자를 찾을 수 없습니다."),

    // 5. 오퍼 관련 오류
    OFFER_LIST_EMPTY(HttpStatus.OK, "OFFER_001", "받은 오퍼 목록 없음"),
    SENT_OFFER_LIST_EMPTY(HttpStatus.OK, "OFFER_002", "보낸 오퍼 목록 없음"); // ★ 새로 추가된 코드

    private final HttpStatus status;
    private final String code;
    private final String message;
}