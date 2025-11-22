package com.enomdeul.domain.user.exception;

import com.enomdeul.global.common.response.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private final BaseErrorCode errorCode;

    public UserException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}