package com.enomdeul.domain.offer.dto.response;

import com.enomdeul.domain.offer.entity.Offer;
import com.enomdeul.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReceivedOfferRes {

    private String name;        // 보낸 사람 이름
    private Integer age;        // 보낸 사람 나이
    private String organization;// 보낸 사람 소속
    private String introduction;// 보낸 사람 한줄소개 (User entity의 intro)
    private Long userId;        // 보낸 사람 ID (senderId)

    public static ReceivedOfferRes from(Offer offer) {
        User sender = offer.getOfferer(); // 오퍼를 보낸 사람 정보를 꺼냄
        return ReceivedOfferRes.builder()
                .name(sender.getName())
                .age(sender.getAge())
                .organization(sender.getOrganization())
                .introduction(sender.getIntro())
                .userId(sender.getUserId())
                .build();
    }
}