package com.enomdeul.domain.offer.dto.request;

import com.enomdeul.domain.offer.entity.OfferStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OfferStatusReq {

    @NotNull(message = "변경할 상태(ACCEPTED/REJECTED)는 필수입니다.")
    private OfferStatus status;
}