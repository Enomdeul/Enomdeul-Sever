package com.enomdeul.domain.sample.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.enomdeul.global.common.entity.BaseTimeEntity;

@Entity
@Table(name = "sample")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sample extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isAnonymous = false;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(nullable = false)
    @Builder.Default
    private Integer likeCount = 0;
}
