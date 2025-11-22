package com.enomdeul.domain.skill.service;

import com.enomdeul.domain.skill.dto.response.DesiredSkillDto;
import com.enomdeul.domain.skill.entity.DesiredSkill;
import com.enomdeul.domain.skill.repository.DesiredSkillRepository;
import com.enomdeul.domain.skill.repository.SkillRepository;
import com.enomdeul.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesiredSkillService {

    private final DesiredSkillRepository desiredSkillRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    public List<DesiredSkillDto> getDesiredSkills(Long userId) {
        List<DesiredSkill> desiredSkills = desiredSkillRepository.findAllByUser_UserId(userId);

        return desiredSkills.stream()
                .map(ds->new DesiredSkillDto(
                        ds.getSkill().getSkillId(),
                        ds.getSkill().getSkillName()
                )).toList();
    }
}
