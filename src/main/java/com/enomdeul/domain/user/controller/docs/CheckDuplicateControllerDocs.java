package com.enomdeul.domain.user.controller.docs;

import com.enomdeul.domain.user.dto.response.CheckEmailRes;
import com.enomdeul.domain.user.dto.response.CheckLoginIdRes;
import com.enomdeul.domain.user.exception.UserErrorCode;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiErrorCode;
import com.enomdeul.global.exception.swagger.ApiErrorCodeExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name="User", description="user 중복 체크 API")
public interface CheckDuplicateControllerDocs {
    @Operation(
            summary = "로그인 ID 중복 확인",
            description = "입력한 loginId가 중복되는지 확인"
    )
    @ApiErrorCodeExamples(
            value = {UserErrorCode.class, ApiErrorCode.class},
            include = {"DUPLICATE_LOGIN_ID", "INTERNAL_SERVER_ERROR"}
    )
    ResponseEntity<ApiResponse<CheckLoginIdRes>>  checkLoginId(@RequestParam String loginId);


    @Operation(
            summary = "이메일 중복 확인",
            description = "입력한 email이 중복되는지 여부 확인"
    )
    @ApiErrorCodeExamples(
            value={UserErrorCode.class, ApiErrorCode.class},
            include = {
                    "DUPLICATE_EMAIL",
                    "INTERNAL_SERVER_ERROR"
            }
    )
    ResponseEntity<ApiResponse<CheckEmailRes>>  checkEmail(@RequestParam String email);
}
