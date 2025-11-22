package com.enomdeul.domain.skill.repository;

import com.enomdeul.domain.skill.entity.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
}
