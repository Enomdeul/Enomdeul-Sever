package com.enomdeul.domain.skill.entity;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DesiredSkillId implements Serializable {
    private Long skill;
    private Long user;
}