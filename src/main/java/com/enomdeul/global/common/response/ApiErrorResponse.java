package com.enomdeul.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.enomdeul.global.common.response.code.BaseErrorCode;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값이면 JSON 응답에서 제외
public class ApiErrorResponse {
    private final String code;
    private final String message;

    public static ApiErrorResponse of(BaseErrorCode errorCode) {
        return new ApiErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }

    public static ApiErrorResponse of(String code, String message) {
        return new ApiErrorResponse(code, message);
    }
}
