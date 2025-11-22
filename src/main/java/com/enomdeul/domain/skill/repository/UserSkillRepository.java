package com.enomdeul.domain.skill.repository;

import com.enomdeul.domain.skill.entity.UserSkill;
import com.enomdeul.domain.skill.entity.UserSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkillId> {
    List<UserSkill> findBySkill_SkillId(Long skillId);
}
