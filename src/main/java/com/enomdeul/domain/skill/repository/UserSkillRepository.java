package com.enomdeul.domain.skill.repository;

import com.enomdeul.domain.skill.entity.Skill;
import com.enomdeul.domain.skill.entity.UserSkill;
import com.enomdeul.domain.skill.entity.UserSkillId;
import com.enomdeul.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkillId> {
    List<UserSkill> findBySkill_SkillId(Long skillId);
    List<UserSkill> findAllByUser(User user);

    @Query("SELECT us.skill FROM UserSkill us WHERE us.user.userId = :userId")
    List<Skill> findSkillsByUserId(@Param("userId") Long userId);
}