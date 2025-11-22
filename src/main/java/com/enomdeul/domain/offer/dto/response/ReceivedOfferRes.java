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
public class ReceivedOfferRes {

    private String name;
    private Integer age;
    private String organization;
    private String introduction;
    private Long userId;
    private OfferStatus status;

    public static ReceivedOfferRes from(Offer offer) {
        User sender = offer.getOfferer();
        return ReceivedOfferRes.builder()
                .name(sender.getName())
                .age(sender.getAge())
                .organization(sender.getOrganization())
                .introduction(sender.getIntro())
                .userId(sender.getUserId())
                .status(offer.getOfferStatus())
                .build();
    }
}