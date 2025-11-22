package com.enomdeul.domain.offer.dto.response;

import com.enomdeul.domain.offer.entity.Offer;
import com.enomdeul.domain.offer.entity.OfferStatus;
import com.enomdeul.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SentOfferRes {

    private String name;
    private Integer age;
    private String organization;
    private String introduction;
    private Long userId;
    private OfferStatus status;

    public static SentOfferRes from(Offer offer) {
        User receiver = offer.getOfferee();
        return SentOfferRes.builder()
                .name(receiver.getName())
                .age(receiver.getAge())
                .organization(receiver.getOrganization())
                .introduction(receiver.getIntro())
                .userId(receiver.getUserId())
                .status(offer.getOfferStatus())
                .build();
    }
}