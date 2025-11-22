package com.enomdeul.domain.sample.exception;

import lombok.Getter;
import com.enomdeul.global.common.response.code.BaseErrorCode;
import com.enomdeul.global.exception.CustomException;

@Getter
public class SampleException extends CustomException {

    public SampleException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
