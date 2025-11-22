package com.enomdeul.domain.skill.controller.docs;

import com.enomdeul.domain.skill.dto.response.DesiredSkillDto;
import com.enomdeul.domain.skill.dto.response.JobGroupSkillResponse;
import com.enomdeul.domain.skill.dto.response.SkillUserResponseDto;
import com.enomdeul.domain.skill.exception.SkillErrorCode;
import com.enomdeul.domain.user.exception.UserErrorCode;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiErrorCode;
import com.enomdeul.global.exception.swagger.ApiErrorCodeExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Skill", description = "스킬 / 사용자 기반 조회 API")
public interface SkillControllerDocs {

    @Operation(
            summary = "직군별 전체 스킬 조회",
            description = "등록된 모든 스킬을 직군 단위로 그룹화하여 반환합니다."
    )
    @ApiErrorCodeExamples(
            value = {SkillErrorCode.class, ApiErrorCode.class},
            include = {"INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR"}
    )
    ApiResponse<List<JobGroupSkillResponse>> getAllSkills();


    @Operation(
            summary = "특정 스킬 기반 사용자 목록 조회",
            description = "선택한 스킬을 보유한 사용자 목록을 조회합니다."
    )
    @ApiErrorCodeExamples(
            value = {SkillErrorCode.class, UserErrorCode.class, ApiErrorCode.class},
            include = {"SKILL_NOT_FOUND", "USER_NOT_FOUND", "INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR"}
    )
    ApiResponse<List<SkillUserResponseDto>> getUsersBySkillId(
            @PathVariable Long skillId
    );


    @Operation(
            summary = "더하고 싶은 스킬 조회",
            description = "로그인한 사용자가 희망하는 스킬 목록을 조회합니다."
    )
    @ApiErrorCodeExamples(
            value = {SkillErrorCode.class, UserErrorCode.class, ApiErrorCode.class},
            include = {"USER_NOT_FOUND", "INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR"}
    )
    ApiResponse<List<DesiredSkillDto>> getAddDesiredSkill(
            @AuthenticationPrincipal String userId
    );
}
