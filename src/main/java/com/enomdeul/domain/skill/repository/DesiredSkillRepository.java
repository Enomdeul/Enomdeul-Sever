package com.enomdeul.domain.skill.repository;

import com.enomdeul.domain.skill.entity.DesiredSkill;
import com.enomdeul.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesiredSkillRepository extends JpaRepository<DesiredSkill, Long> {
    List<DesiredSkill> findAllByUser(User user);
}
