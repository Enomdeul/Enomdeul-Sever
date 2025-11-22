package com.enomdeul.domain.offer.dto.response;

import com.enomdeul.domain.offer.entity.Offer;
import com.enomdeul.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SentOfferRes {

    private String name;        // 받는 사람 이름
    private Integer age;        // 받는 사람 나이
    private String organization;// 받는 사람 소속
    private String introduction;// 받는 사람 한줄소개
    private Long userId;        // 받는 사람 ID (Receiver ID)

    public static SentOfferRes from(Offer offer) {
        User receiver = offer.getOfferee();
        return SentOfferRes.builder()
                .name(receiver.getName())
                .age(receiver.getAge())
                .organization(receiver.getOrganization())
                .introduction(receiver.getIntro())
                .userId(receiver.getUserId())
                .build();
    }
}