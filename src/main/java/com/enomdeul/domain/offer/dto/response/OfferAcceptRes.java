package com.enomdeul.domain.offer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OfferAcceptRes {
    private String email; // 수락 시 상대방 이메일 공개
}