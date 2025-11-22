package com.enomdeul.domain.sample.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import com.enomdeul.domain.sample.repository.SampleRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepositoryCustom {

    private final JPAQueryFactory queryFactory;
}
