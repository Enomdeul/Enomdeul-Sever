package com.enomdeul.domain.skill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SkillLevel {
    HIGH("상"), MIDDLE("중"), LOW("하");

    private final String description;
}
