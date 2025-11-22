package com.enomdeul.domain.offer.exception;

import com.enomdeul.global.common.response.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OfferErrorCode implements BaseErrorCode {

    OFFER_NOT_FOUND("OFFER_NOT_FOUND", HttpStatus.NOT_FOUND, "해당 매칭을 찾을 수 없습니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
