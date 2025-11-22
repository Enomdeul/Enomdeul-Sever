package com.enomdeul.global.exception;

import com.enomdeul.domain.user.exception.UserException; // 도메인 예외
import com.enomdeul.global.common.response.ApiErrorResponse;
import com.enomdeul.global.common.response.code.ApiErrorCode; // 기존에 있던 것 (호환성 유지)
import com.enomdeul.global.common.response.code.BaseErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. GlobalException 처리 (공통 예외)
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ApiErrorResponse> handleGlobalException(GlobalException e) {
        log.warn("GlobalException : {}", e.getMessage());
        return buildErrorResponse(e.getErrorCode());
    }

    // 2. UserException 처리 (유저 도메인 예외)
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiErrorResponse> handleUserException(UserException e) {
        log.warn("UserException : {}", e.getMessage());
        return buildErrorResponse(e.getErrorCode());
    }

    // 3. CustomException 처리 (기존에 있던 것 유지)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomException(CustomException e) {
        log.error("CustomException : {}", e.getMessage());
        return buildErrorResponse(e.getErrorCode());
    }

    // 4. 그 외 모든 예외 처리 (예상치 못한 에러 -> 500 INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnknownException(Exception e) {
        e.printStackTrace();
        log.error("Unhandled Exception : {}", e.getMessage());
        // GlobalErrorCode 또는 기존 ApiErrorCode 사용
        return buildErrorResponse(GlobalErrorCode.INTERNAL_SERVER_ERROR);
    }

    // 5. 어노테이션 유효성 체크 (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleArgumentException(MethodArgumentNotValidException e) {
        return buildErrorResponse(mapFieldErrorToErrorCode(e.getFieldError()));
    }

    // 6. Enum 파싱 에러
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleEnumParseError(MethodArgumentTypeMismatchException e) {
        log.error("Enum Parse Error : {}", e.getMessage());
        return buildErrorResponse(ApiErrorCode.TYPE_MISMATCH_ERROR);
    }

    // 7. 날짜 파싱 에러
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ApiErrorResponse> handleDateParseError(DateTimeParseException e) {
        log.error("Date Parse Error : {}", e.getMessage());
        return buildErrorResponse(ApiErrorCode.DATE_INVALID_ERROR);
    }

    // --- Helper Methods ---

    private ResponseEntity<ApiErrorResponse> buildErrorResponse(BaseErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiErrorResponse.of(errorCode));
    }

    private ResponseEntity<ApiErrorResponse> buildErrorResponse(ApiErrorResponse errorResponse) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    private ApiErrorResponse mapFieldErrorToErrorCode(FieldError error) {
        String message = error.getDefaultMessage();
        return ApiErrorResponse.of(generateErrorCode(error), message);
    }

    private String generateErrorCode(FieldError error) {
        String field = error.getField();
        String code = error.getCode();
        return (field + "_" + code + "_" + "error").toUpperCase();
    }
}