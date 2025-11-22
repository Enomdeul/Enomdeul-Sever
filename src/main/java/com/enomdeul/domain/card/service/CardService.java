package com.enomdeul.domain.card.service;

import com.enomdeul.domain.card.dto.UserCardReq;
import com.enomdeul.domain.skill.entity.DesiredSkill;
import com.enomdeul.domain.skill.entity.Skill;
import com.enomdeul.domain.skill.entity.UserSkill;
import com.enomdeul.domain.skill.exception.SkillErrorCode;
import com.enomdeul.domain.skill.exception.SkillException;
import com.enomdeul.domain.skill.repository.DesiredSkillRepository;
import com.enomdeul.domain.skill.repository.SkillRepository;
import com.enomdeul.domain.skill.repository.UserSkillRepository;
import com.enomdeul.domain.user.entity.User;
import com.enomdeul.domain.user.exception.UserErrorCode;
import com.enomdeul.domain.user.exception.UserException;
import com.enomdeul.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;
    private final DesiredSkillRepository desiredSkillRepository;

    public void createUserCard(Long userId, UserCardReq request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        user.updateCardInfo(
                request.getName(),
                request.getGender(),
                request.getAge(),
                request.getOrganization(),
                request.getJobGroup(),
                request.getIntroduction()
        );

        // 보유 스킬 등록
        List<UserSkill> userSkills = request.getSkills().stream()
                .map(skillReq -> {
                    Skill skill = skillRepository.findById(skillReq.getSkillId())
                            .orElseThrow(() -> new SkillException(SkillErrorCode.SKILL_NOT_FOUND));

                    return UserSkill.builder()
                            .user(user)
                            .skill(skill)
                            .build();
                })
                .toList();

        userSkillRepository.saveAll(userSkills);

        // 더하고 싶은 스킬 등록
        List<DesiredSkill> desiredSkills = request.getDesiredSkills().stream()
                .map(dsReq -> {
                    Skill skill = skillRepository.findById(dsReq.getSkillId())
                            .orElseThrow(() -> new SkillException(SkillErrorCode.SKILL_NOT_FOUND));

                    return DesiredSkill.builder()
                            .user(user)
                            .skill(skill)
                            .build();
                })
                .toList();

        desiredSkillRepository.saveAll(desiredSkills);
    }
}


