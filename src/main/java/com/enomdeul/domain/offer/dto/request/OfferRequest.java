package com.enomdeul.domain.offer.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OfferRequest {

    @NotNull(message = "상대방 ID는 필수 입력값입니다.") // Long 타입은 @NotNull 사용
    private Long offereeId;
}