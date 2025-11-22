package com.enomdeul.domain.skill.exception;

import com.enomdeul.global.common.response.code.BaseErrorCode;
import com.enomdeul.global.exception.CustomException;
import lombok.Getter;

@Getter
public class SkillException extends CustomException {

    public SkillException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
