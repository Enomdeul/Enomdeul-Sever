package com.enomdeul.domain.user.controller;

import com.enomdeul.domain.user.controller.docs.UserControllerDocs;
import com.enomdeul.domain.user.dto.request.UserLoginReq;
import com.enomdeul.domain.user.dto.request.UserSignupReq;
import com.enomdeul.domain.user.dto.response.UserLoginRes;
import com.enomdeul.domain.user.dto.response.UserSignupRes;
import com.enomdeul.domain.user.service.UserService;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController implements UserControllerDocs {

    private final UserService userService;

    @Override
    @PostMapping("/signup")
    public ApiResponse<UserSignupRes> signup(@RequestBody UserSignupReq req) {
        UserSignupRes result = userService.signup(req);
        return ApiResponse.of(ApiSuccessCode.SUCCESS, result);
    }

    @Override
    @PostMapping("/login")
    public ApiResponse<UserLoginRes> login(@RequestBody UserLoginReq req) {
        UserLoginRes result = userService.login(req);
        return ApiResponse.of(ApiSuccessCode.SUCCESS, result);
    }
}