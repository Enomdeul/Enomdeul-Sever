package com.enomdeul.domain.skill.exception;

import com.enomdeul.global.common.response.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SkillErrorCode implements BaseErrorCode {

    SKILL_NOT_FOUND("SKILL_NOT_FOUND", HttpStatus.NOT_FOUND, "스킬을 찾을 수 없습니다."),
    USER_NOT_FOUND("USER_NOT_FOUND", HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");
    private final String code;
    private final HttpStatus status;
    private final String message;
}
