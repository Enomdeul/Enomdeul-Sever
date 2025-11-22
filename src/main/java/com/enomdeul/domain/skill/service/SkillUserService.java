package com.enomdeul.domain.skill.service;

import com.enomdeul.domain.skill.dto.response.SkillUserResponseDto;
import com.enomdeul.domain.skill.entity.Skill;
import com.enomdeul.domain.skill.entity.UserSkill;
import com.enomdeul.domain.skill.exception.SkillErrorCode;
import com.enomdeul.domain.skill.repository.SkillRepository;
import com.enomdeul.domain.skill.repository.UserSkillRepository;
import com.enomdeul.domain.user.entity.User;
import com.enomdeul.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkillUserService {
    private final UserSkillRepository userSkillRepository;
    private final SkillRepository skillRepository;

    public List<SkillUserResponseDto> getUserBySkillId(Long skillId) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new CustomException(SkillErrorCode.SKILL_NOT_FOUND));

        List<UserSkill> userSkills = userSkillRepository.findBySkill_SkillId(skillId);

        if(userSkills.isEmpty()){
            throw new CustomException(SkillErrorCode.USER_NOT_FOUND);
        }

        return userSkills.stream()
                .map(us->{
                    User user = us.getUser();
                    return new SkillUserResponseDto(
                            user.getName(),
                            user.getAge(),
                            user.getOrganization(),
                            user.getIntro(),
                            user.getUserId()
                    );
                })
                .collect(Collectors.toList());
    }
}
