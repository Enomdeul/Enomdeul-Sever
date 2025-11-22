package com.enomdeul.domain.skill.dto.response;

public record SkillUserResponseDto(
        String name,
        int age,
        String organization,
        String intro,
        Long user_id
) {
}
