package com.enomdeul.domain.offer.dto.response;

import com.enomdeul.domain.offer.entity.Offer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class OfferResponse {
    private Long senderId;      // 오퍼를 보낸 사람 (나)
    private Long receiverId;    // 오퍼를 받은 사람 (상대방)
    private LocalDateTime createdAt; // 오퍼 생성 시간

    public static OfferResponse from(Offer offer) {
        return OfferResponse.builder()
                .senderId(offer.getOfferer().getUserId())
                .receiverId(offer.getOfferee().getUserId())
                .createdAt(offer.getCreatedAt())
                .build();
    }
}