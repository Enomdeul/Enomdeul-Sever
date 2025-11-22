package com.enomdeul.domain.card.dto.request;

import com.enomdeul.domain.skill.enums.JobGroup;
import com.enomdeul.domain.user.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserCardReq {

    @NotBlank(message = "이름은 필수 값입니다.")
    private String name;

    @NotNull(message = "성별은 필수 값입니다.")
    private Gender gender;

    @NotNull(message = "나이는 필수 값입니다.")
    @Min(value = 1, message = "나이는 1 이상이어야 합니다.")
    private Integer age;

    @NotBlank(message = "소속(학교/회사)은 필수 값입니다.")
    private String organization;

    @NotNull(message = "직군은 필수 값입니다.")
    private JobGroup jobGroup;

    @NotNull(message = "한 줄 소개는 필수 값입니다.")
    @Size(max = 75, message = "한 줄 소개는 75자 이하여야 합니다.")
    private String introduction;

    @NotEmpty(message = "보유 스킬 리스트는 비어 있을 수 없습니다.")
    private List<SkillRequest> skills;

    @NotEmpty(message = "더하기 스킬 리스트는 비어 있을 수 없습니다.")
    private List<SkillRequest> desiredSkills;

    @Getter
    @NoArgsConstructor
    public static class SkillRequest {

        @NotNull(message = "스킬 ID는 필수 값입니다.")
        private Long skillId;
    }
}
