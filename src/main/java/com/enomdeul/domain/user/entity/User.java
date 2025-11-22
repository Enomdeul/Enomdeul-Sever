package com.enomdeul.domain.user.entity;

import com.enomdeul.domain.skill.enums.JobGroup;
import com.enomdeul.domain.user.enums.Gender;
import com.enomdeul.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "`user`") // DB 예약어라 백틱 필수
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // 쿼리와 일치
    private Long userId;

    @Column(length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Column(length = 100)
    private String password;

    @Column(name = "login_id", length = 30)
    private String loginId;

    @Column(length = 30)
    private String email;

    @Column(length = 30)
    private String organization;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_group", length = 30)
    private JobGroup jobGroup;

    @Column(length = 255)
    private String intro;

    // 유저 카드 업데이트
    public void updateCardInfo(String name, Gender gender, Integer age, String organization, JobGroup jobGroup, String intro) {

        this.name = name;
        this.gender = gender;
        this.age = age;
        this.organization = organization;
        this.jobGroup = jobGroup;
        this.intro = intro;
    }

}