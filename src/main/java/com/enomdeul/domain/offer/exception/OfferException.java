package com.enomdeul.domain.offer.exception;

import com.enomdeul.global.common.response.code.BaseErrorCode;
import com.enomdeul.global.exception.CustomException;
import lombok.Getter;

@Getter
public class OfferException extends RuntimeException {
    private final BaseErrorCode errorCode;

    public OfferException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}