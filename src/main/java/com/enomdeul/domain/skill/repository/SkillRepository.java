package com.enomdeul.domain.skill.repository;

import com.enomdeul.domain.skill.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByJobGroup(String jobGroup);
}
