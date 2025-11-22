package com.enomdeul.domain.skill.service;

import com.enomdeul.domain.skill.dto.response.DesiredSkillDto;
import com.enomdeul.domain.skill.entity.Skill;
import com.enomdeul.domain.skill.exception.SkillErrorCode;
import com.enomdeul.domain.skill.repository.SkillRepository;
import com.enomdeul.domain.skill.repository.UserSkillRepository;
import com.enomdeul.domain.user.entity.User;
import com.enomdeul.domain.user.repository.UserRepository;
import com.enomdeul.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendSkillService {
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;

    public List<DesiredSkillDto> recommendSkills(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(SkillErrorCode.USER_NOT_FOUND));

        List<Skill> skillsInGroup = skillRepository.findByJobGroup(user.getJobGroup().name());

        List<Skill> userSkills = userSkillRepository.findSkillsByUserId(userId);

        Set<Long> userSkillIds = userSkills.stream()
                .map(Skill::getSkillId)
                .collect(Collectors.toSet());

        List<DesiredSkillDto> recommendations = skillsInGroup.stream()
                .filter(skill -> !userSkillIds.contains(skill.getSkillId()))
                .limit(3)
                .map(skill -> new DesiredSkillDto(
                        skill.getSkillId(),
                        skill.getSkillName()
                ))
                .toList();

        return recommendations;
    }
}
