package com.enomdeul.domain.user.controller;

import com.enomdeul.domain.user.controller.docs.CheckDuplicateControllerDocs;
import com.enomdeul.domain.user.dto.response.CheckEmailRes;
import com.enomdeul.domain.user.dto.response.CheckLoginIdRes;
import com.enomdeul.domain.user.service.CheckDuplicateService;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class CheckDuplicateController implements CheckDuplicateControllerDocs {
    private final CheckDuplicateService checkDuplicateService;

    @Override
    @GetMapping("/check-id")
    public ResponseEntity<ApiResponse<CheckLoginIdRes>> checkLoginId(
            @RequestParam String loginId
    ){
        CheckLoginIdRes result = checkDuplicateService.isLoginIdAvailable(loginId);

        return ResponseEntity.ok(ApiResponse.of(ApiSuccessCode.SUCCESS, result));
    }

    @Override
    @GetMapping("/check-email")
    public ResponseEntity<ApiResponse<CheckEmailRes>> checkEmail(
            @RequestParam String email){
        CheckEmailRes result = checkDuplicateService.isEmailAvailable(email);
        return ResponseEntity.ok(ApiResponse.of(ApiSuccessCode.SUCCESS, result));
    }
}
