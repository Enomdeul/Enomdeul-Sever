package com.enomdeul.domain.card.dto;

import com.enomdeul.domain.skill.enums.JobGroup;
import com.enomdeul.domain.user.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserCardReq {
    private String name;
    private Gender gender;
    private Integer age;
    private String organization;
    private JobGroup jobGroup;
    private String introduction;
    private String portfolioUrl;

    // 보유 스킬 리스트
    private List<SkillRequest> skills;

    // 더하고 싶은 스킬 리스트
    private List<SkillRequest> desiredSkills;

    @Getter
    @NoArgsConstructor
    public static class SkillRequest {
        private Long skillId;
    }
}
