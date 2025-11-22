package com.enomdeul.domain.card.docs;

import com.enomdeul.domain.card.dto.UserCardReq;
import com.enomdeul.domain.skill.exception.SkillErrorCode;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiErrorCode;
import com.enomdeul.global.exception.swagger.ApiErrorCodeExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Card", description = "카드 관련 API")
public interface CardControllerDocs {

    @Operation(summary = "유저 카드 생성", description = "유저 카드를 생성합니다.")
    @ApiErrorCodeExamples(
            value = {SkillErrorCode.class, ApiErrorCode.class},
            include = { "INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR", "SKILL_NOT_FOUND"})
    ResponseEntity<ApiResponse<String>> createUserCard(@AuthenticationPrincipal String userId, @RequestBody UserCardReq request);

}
