package com.enomdeul.domain.offer.dto.request;

import com.enomdeul.domain.offer.entity.OfferStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OfferStatusReq {
    private OfferStatus status;
}