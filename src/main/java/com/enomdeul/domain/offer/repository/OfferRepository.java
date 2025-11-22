package com.enomdeul.domain.offer.repository;

import com.enomdeul.domain.offer.entity.Offer;
import com.enomdeul.domain.offer.entity.OfferId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, OfferId> {

    @Query("SELECT o FROM Offer o JOIN FETCH o.offerer WHERE o.offeree.userId = :receiverId")
    List<Offer> findAllReceivedOffers(@Param("receiverId") Long receiverId);

    @Query("SELECT o FROM Offer o JOIN FETCH o.offeree WHERE o.offerer.userId = :senderId")
    List<Offer> findAllSentOffers(@Param("senderId") Long senderId);
}