package com.enomdeul.global.exception;

import lombok.Getter;
import com.enomdeul.global.common.response.code.BaseErrorCode;

@Getter
public class CustomException extends RuntimeException {
    private final BaseErrorCode errorCode;

    public CustomException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
