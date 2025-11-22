package com.enomdeul.domain.user.controller.docs;

import com.enomdeul.domain.user.dto.request.UserLoginReq;
import com.enomdeul.domain.user.dto.request.UserSignupReq;
import com.enomdeul.domain.user.dto.response.UserLoginRes;
import com.enomdeul.domain.user.dto.response.UserSignupRes;
import com.enomdeul.domain.user.exception.UserErrorCode;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiErrorCode;
import com.enomdeul.global.exception.swagger.ApiErrorCodeExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "유저 관련 API")
public interface UserControllerDocs {

    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    @ApiErrorCodeExamples(
            value = {UserErrorCode.class, ApiErrorCode.class},
            // 발생 가능한 에러 코드들을 적어줍니다.
            include = {
                    "DUPLICATE_LOGIN_ID",
                    "DUPLICATE_EMAIL",
                    "INTERNAL_SERVER_ERROR"
            }
    )
    ApiResponse<UserSignupRes> signup(@RequestBody UserSignupReq req);


    @Operation(summary = "로그인", description = "로그인을 진행하고 토큰을 발급받습니다.")
    @ApiErrorCodeExamples(
            value = {UserErrorCode.class, ApiErrorCode.class},
            include = {
                    "USER_NOT_FOUND",      // "존재하지 않는 아이디" 에러 코드 (Enum에 추가 필요)
                    "PASSWORD_NOT_MATCH",  // "비밀번호 불일치" 에러 코드 (Enum에 추가 필요)
                    "INTERNAL_SERVER_ERROR"
            }
    )
    ApiResponse<UserLoginRes> login(@RequestBody UserLoginReq req);
}