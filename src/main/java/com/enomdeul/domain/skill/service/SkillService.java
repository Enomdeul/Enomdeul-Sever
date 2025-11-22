package com.enomdeul.domain.skill.service;

import com.enomdeul.domain.skill.dto.response.JobGroupSkillResponse;
import com.enomdeul.domain.skill.dto.response.SkillItemDto;
import com.enomdeul.domain.skill.entity.Skill;
import com.enomdeul.domain.skill.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public List<JobGroupSkillResponse> getSkillsByJobGroup() {
        List<Skill> allSkills = skillRepository.findAll();

        Map<String, List<Skill>> grouped=allSkills.stream()
                .collect(Collectors.groupingBy(Skill::getJobGroup));

        List<JobGroupSkillResponse> responses =new ArrayList<>();

        grouped.forEach((jobGroup,skills)->{
            responses.add(new JobGroupSkillResponse(
                    jobGroup,
                    skills.stream()
                            .map(SkillItemDto::from)
                            .collect(Collectors.toList())
            ));
        });

        return responses;
    }
}
