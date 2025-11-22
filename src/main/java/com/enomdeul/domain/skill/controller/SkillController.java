package com.enomdeul.domain.skill.controller;

import com.enomdeul.domain.skill.controller.docs.SkillControllerDocs;
import com.enomdeul.domain.skill.dto.response.DesiredSkillDto;
import com.enomdeul.domain.skill.dto.response.JobGroupSkillResponse;
import com.enomdeul.domain.skill.dto.response.SkillUserResponseDto;
import com.enomdeul.domain.skill.service.DesiredSkillService;
import com.enomdeul.domain.skill.service.RecommendSkillService;
import com.enomdeul.domain.skill.service.SkillService;
import com.enomdeul.domain.skill.service.SkillUserService;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/skills")
public class SkillController implements SkillControllerDocs {
    private final SkillService skillService;
    private final SkillUserService skillUserService;
    private final DesiredSkillService desiredSkillService;
    private final RecommendSkillService recommendSkillService;

    @Override
    @GetMapping
    public ApiResponse<List<JobGroupSkillResponse>> getAllSkills(
    ){
        return ApiResponse.of(ApiSuccessCode.SUCCESS,skillService.getSkillsByJobGroup());
    }

    @Override
    @GetMapping("/{skillId}/users")
    public ApiResponse<List<SkillUserResponseDto>> getUsersBySkillId(
            @PathVariable Long skillId
    ){
        List<SkillUserResponseDto>users=skillUserService.getUserBySkillId(skillId);
        return ApiResponse.of(ApiSuccessCode.SUCCESS,users);
    }

    @Override
    @GetMapping("/desired")
    public ApiResponse<List<DesiredSkillDto>> getAddDesiredSkill(
            @AuthenticationPrincipal String userId
    ){
        Long id=Long.parseLong(userId);
        List<DesiredSkillDto> desiredSkills = desiredSkillService.getDesiredSkills(id);

        return ApiResponse.of(ApiSuccessCode.SUCCESS,desiredSkills);
    }

    @GetMapping("/recommended")
    public ApiResponse<List<DesiredSkillDto>> getRecommendedSkills(
            @AuthenticationPrincipal String userId
    ){
        Long id=Long.parseLong(userId);
        List<DesiredSkillDto> recommended = recommendSkillService.recommendSkills(id);

        return ApiResponse.of(ApiSuccessCode.SUCCESS,recommended);

    }
}
