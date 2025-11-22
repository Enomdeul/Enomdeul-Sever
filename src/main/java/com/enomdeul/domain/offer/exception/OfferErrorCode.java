package com.enomdeul.domain.offer.exception;

import com.enomdeul.global.common.response.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OfferErrorCode implements BaseErrorCode {
    // 오퍼 관련 비즈니스 예외
    OFFER_NOT_FOUND(HttpStatus.NOT_FOUND, "OFFER_003", "해당 매칭을 찾을 수 없습니다."),
    ALREADY_SENT_OFFER(HttpStatus.BAD_REQUEST, "OFFER_004", "이미 오퍼를 보낸 상대입니다."),
    CANNOT_OFFER_SELF(HttpStatus.BAD_REQUEST, "OFFER_005", "자기 자신에게는 오퍼를 보낼 수 없습니다."),
    ALREADY_PROCESSED_OFFER(HttpStatus.BAD_REQUEST, "OFFER_006", "이미 수락하거나 거절한 오퍼입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}