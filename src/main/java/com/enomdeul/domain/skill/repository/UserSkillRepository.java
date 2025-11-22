package com.enomdeul.domain.skill.repository;

import com.enomdeul.domain.skill.entity.UserSkill;
import com.enomdeul.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
    List<UserSkill> findAllByUser(User user);
}
