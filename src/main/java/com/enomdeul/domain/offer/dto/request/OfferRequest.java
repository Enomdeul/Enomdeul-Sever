package com.enomdeul.domain.offer.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OfferRequest {
    private Long offereeId; // 오퍼를 받을 상대방 ID
}