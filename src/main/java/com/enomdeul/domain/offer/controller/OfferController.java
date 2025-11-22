package com.enomdeul.domain.offer.controller;

import com.enomdeul.domain.offer.dto.request.OfferRequest;
import com.enomdeul.domain.offer.dto.response.OfferResponse;
import com.enomdeul.domain.offer.service.OfferService;
import com.enomdeul.global.common.response.ApiResponse; // 공통 응답 객체 경로 가정
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ApiResponse<OfferResponse> sendOffer(
            @AuthenticationPrincipal String userId,
            @RequestBody OfferRequest request
    ) {
        Long senderId = Long.parseLong(userId); // String -> Long 변환

        OfferResponse response = offerService.sendOffer(senderId, request);
        return ApiResponse.onSuccess(response);
    }
}