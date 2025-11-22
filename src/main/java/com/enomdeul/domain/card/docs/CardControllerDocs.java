package com.enomdeul.domain.card.docs;

import com.enomdeul.domain.card.dto.request.UserCardReq;
import com.enomdeul.domain.card.dto.response.UserCardRes;
import com.enomdeul.domain.skill.exception.SkillErrorCode;
import com.enomdeul.domain.user.exception.UserErrorCode;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiErrorCode;
import com.enomdeul.global.exception.swagger.ApiErrorCodeExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Card", description = "카드 관련 API")
public interface CardControllerDocs {

    @Operation(summary = "유저 카드 생성", description = "유저 카드를 생성합니다.")
    @ApiErrorCodeExamples(
            value = {SkillErrorCode.class, ApiErrorCode.class},
            include = { "INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR", "SKILL_NOT_FOUND", "INTRODUCTION_SIZE_ERROR"})
    ResponseEntity<ApiResponse<String>> createUserCard(@AuthenticationPrincipal String userId, @RequestBody UserCardReq request);

    @Operation(summary = "유저 카드 조회(본인)", description = "본인 카드를 조회합니다.")
    @ApiErrorCodeExamples(
            value = {SkillErrorCode.class, UserErrorCode.class, ApiErrorCode.class},
            include = { "INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR", "SKILL_NOT_FOUND", "INTRODUCTION_SIZE_ERROR", "USER_NOT_FOUND"})
    ResponseEntity<ApiResponse<UserCardRes>> findUserCard(@AuthenticationPrincipal String id);

    @Operation(summary = "유저 카드 조회(타인)", description = "다른 유저의 카드를 조회합니다.")
    @ApiErrorCodeExamples(
            value = {SkillErrorCode.class, UserErrorCode.class, ApiErrorCode.class},
            include = { "INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR", "SKILL_NOT_FOUND", "USER_NOT_FOUND"})
    ResponseEntity<ApiResponse<UserCardRes>> findUserCard(@PathVariable Long userId);

}
