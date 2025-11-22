package com.enomdeul.domain.skill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JobGroup {

    PM("기획"), DESIGNER("디자인"), DEVELOPER("개발");

    private final String description;
}
