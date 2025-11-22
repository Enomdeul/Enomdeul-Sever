package com.enomdeul.domain.offer.repository;

import com.enomdeul.domain.offer.entity.Offer;
import com.enomdeul.domain.offer.entity.OfferId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, OfferId> {
    // 필요한 경우 중복 오퍼 확인 메소드 추가 가능
    // boolean existsByOffererAndOfferee(User offerer, User offeree);
}