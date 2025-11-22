package com.enomdeul.domain.offer.repository;

import com.enomdeul.domain.offer.entity.Offer;
import com.enomdeul.domain.offer.entity.OfferId;
import com.enomdeul.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, OfferId> {

    // 1. 내가(receiverId) 받은 오퍼 목록 조회 (Fetch Join: Offerer)
    @Query("SELECT o FROM Offer o JOIN FETCH o.offerer WHERE o.offeree.userId = :receiverId")
    List<Offer> findAllReceivedOffers(@Param("receiverId") Long receiverId);

    // 2. 내가(senderId) 보낸 오퍼 목록 조회 (Fetch Join: Offeree)
    @Query("SELECT o FROM Offer o JOIN FETCH o.offeree WHERE o.offerer.userId = :senderId")
    List<Offer> findAllSentOffers(@Param("senderId") Long senderId);

    // 3. 중복 오퍼 확인 (보낸사람, 받는사람 조합이 이미 있는지)
    boolean existsByOffererAndOfferee(User offerer, User offeree);
}