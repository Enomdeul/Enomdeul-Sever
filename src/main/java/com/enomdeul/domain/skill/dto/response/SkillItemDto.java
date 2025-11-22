package com.enomdeul.domain.skill.dto.response;

import com.enomdeul.domain.skill.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SkillItemDto {
    private Long skill_id;
    private String skill_name;

    public static SkillItemDto from(Skill skill) {
        return new SkillItemDto(
                skill.getSkillId(),
                skill.getSkillName()
        );
    }
}

