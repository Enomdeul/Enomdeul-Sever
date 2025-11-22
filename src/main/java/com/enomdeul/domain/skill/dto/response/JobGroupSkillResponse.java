package com.enomdeul.domain.skill.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class JobGroupSkillResponse {
    private String jobGroup;
    private List<SkillItemDto> skills;
}

