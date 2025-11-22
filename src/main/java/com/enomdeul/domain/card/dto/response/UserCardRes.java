package com.enomdeul.domain.card.dto.response;

import com.enomdeul.domain.skill.enums.JobGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserCardRes {
    private String name;
    private Integer age;
    private String organization;
    private JobGroup jobGroup;
    private String introduction;

    // 보유 스킬 리스트
    private List<SkillRequest> skills;

    // 더하고 싶은 스킬 리스트
    private List<SkillRequest> desiredSkills;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SkillRequest {
        private Long skillId;
        private String skillName;
    }
}
