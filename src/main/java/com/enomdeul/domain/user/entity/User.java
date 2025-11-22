package com.enomdeul.domain.user.entity;

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

    @Column(length = 5)
    private String gender;

    @Column(length = 100)
    private String password;

    @Column(name = "login_id", length = 30)
    private String loginId;

    @Column(length = 30)
    private String email;

    @Column(length = 30)
    private String organization;

    private Integer age;

    @Column(name = "job_group", length = 30)
    private String jobGroup;

    @Column(length = 255)
    private String intro;

    @Column(name = "portfolio_url", length = 255)
    private String portfolioUrl;
}