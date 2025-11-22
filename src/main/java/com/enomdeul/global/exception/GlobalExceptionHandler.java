package com.enomdeul.global.exception;

import com.enomdeul.domain.offer.exception.OfferException; // ★ 오퍼 예외 임포트
import com.enomdeul.domain.user.exception.UserException;
import com.enomdeul.global.common.response.ApiErrorResponse;
import com.enomdeul.global.common.response.code.ApiErrorCode;
import com.enomdeul.global.common.response.code.BaseErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. GlobalException 처리
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ApiErrorResponse> handleGlobalException(GlobalException e) {
        log.warn("GlobalException : {}", e.getMessage());
        return buildErrorResponse(e.getErrorCode());
    }

    // 2. UserException 처리
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiErrorResponse> handleUserException(UserException e) {
        log.warn("UserException : {}", e.getMessage());
        return buildErrorResponse(e.getErrorCode());
    }

    // 2-1. OfferException 처리 (오퍼 관련 예외)
    @ExceptionHandler(OfferException.class)
    public ResponseEntity<ApiErrorResponse> handleOfferException(OfferException e) {
        log.warn("OfferException : {}", e.getMessage());
        return buildErrorResponse(e.getErrorCode());
    }

    // 3. CustomException 처리 (기존 유지)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomException(CustomException e) {
        log.error("CustomException : {}", e.getMessage());
        return buildErrorResponse(e.getErrorCode());
    }

    // 4. 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnknownException(Exception e) {
        e.printStackTrace();
        log.error("Unhandled Exception : {}", e.getMessage());
        return buildErrorResponse(GlobalErrorCode.INTERNAL_SERVER_ERROR);
    }

    // 5. 어노테이션 유효성 체크 (@Valid) - 수정됨
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleArgumentException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();

        // DTO에 설정한 message를 가져옴 (예: "아이디는 필수 입력값입니다.")
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "잘못된 요청입니다.";
        log.warn("Validation Error: {}", errorMessage);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse("BAD_REQUEST", errorMessage));
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

    // 8. JSON 파싱 에러
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleJsonErrors(HttpMessageNotReadableException e) {
        log.error("JSON Parse Error: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse("BAD_REQUEST", "JSON 요청 형식이 올바르지 않습니다."));
    }

    // --- Helper Methods ---

    private ResponseEntity<ApiErrorResponse> buildErrorResponse(BaseErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiErrorResponse.of(errorCode));
    }
}