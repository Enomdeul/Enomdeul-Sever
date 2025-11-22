package com.enomdeul.domain.skill.controller;

import com.enomdeul.domain.skill.dto.response.JobGroupSkillResponse;
import com.enomdeul.domain.skill.dto.response.SkillUserResponseDto;
import com.enomdeul.domain.skill.service.SkillService;
import com.enomdeul.domain.skill.service.SkillUserService;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/skills")
public class SkillController {
    private final SkillService skillService;
    private final SkillUserService skillUserService;

    @GetMapping
    public ApiResponse<List<JobGroupSkillResponse>> getAllSkills(
    ){
        return ApiResponse.of(ApiSuccessCode.SUCCESS,skillService.getSkillsByJobGroup());
    }


    @GetMapping("/{skillId}/users")
    public ApiResponse<List<SkillUserResponseDto>> getUsersBySkillId(
            @PathVariable Long skillId
    ){
        List<SkillUserResponseDto>users=skillUserService.getUserBySkillId(skillId);
        return ApiResponse.of(ApiSuccessCode.SUCCESS,users);
    }
}
