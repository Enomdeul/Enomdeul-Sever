package com.enomdeul.domain.offer.entity;

import com.enomdeul.domain.user.entity.User;
import com.enomdeul.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "offer_status")
    private Boolean offerStatus;
}