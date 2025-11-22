package com.enomdeul.global.exception;

import com.enomdeul.global.common.response.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final BaseErrorCode errorCode;

    public GlobalException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}