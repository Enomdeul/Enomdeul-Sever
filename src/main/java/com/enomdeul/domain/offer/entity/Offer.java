package com.enomdeul.domain.offer.entity;

import com.enomdeul.domain.user.entity.User;
import com.enomdeul.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "offer")
@IdClass(OfferId.class)
public class Offer extends BaseTimeEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offerer")
    private User offerer;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offeree")
    private User offeree;

    @Enumerated(EnumType.STRING)
    @Column(name = "offer_status")
    private OfferStatus offerStatus;

    // 상태 변경 편의 메소드
    public void changeStatus(OfferStatus status) {
        this.offerStatus = status;
    }
}