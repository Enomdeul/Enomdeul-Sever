package com.enomdeul.domain.offer.controller;

import com.enomdeul.domain.offer.dto.request.OfferRequest;
import com.enomdeul.domain.offer.dto.request.OfferStatusReq;
import com.enomdeul.domain.offer.dto.response.OfferAcceptRes;
import com.enomdeul.domain.offer.dto.response.OfferResponse;
import com.enomdeul.domain.offer.dto.response.ReceivedOfferRes;
import com.enomdeul.domain.offer.dto.response.SentOfferRes;
import com.enomdeul.domain.offer.service.OfferService;
import com.enomdeul.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offers")
public class OfferController {

    private final OfferService offerService;

    // 1. 오퍼 전송
    @PostMapping
    public ApiResponse<OfferResponse> sendOffer(
            @AuthenticationPrincipal String userId,
            @RequestBody OfferRequest request
    ) {
        Long senderId = Long.parseLong(userId);
        OfferResponse response = offerService.sendOffer(senderId, request);
        return ApiResponse.onSuccess(response);
    }

    // 2. 받은 오퍼 조회
    @GetMapping("/received")
    public ApiResponse<List<ReceivedOfferRes>> getReceivedOffers(@AuthenticationPrincipal String userId) {
        Long receiverId = Long.parseLong(userId);
        List<ReceivedOfferRes> response = offerService.getReceivedOffers(receiverId);
        return ApiResponse.onSuccess(response);
    }

    // 3. 보낸 오퍼 조회
    @GetMapping("/sent")
    public ApiResponse<List<SentOfferRes>> getSentOffers(@AuthenticationPrincipal String userId) {
        Long senderId = Long.parseLong(userId);
        List<SentOfferRes> response = offerService.getSentOffers(senderId);
        return ApiResponse.onSuccess(response);
    }

    // 받은 매칭 오퍼 수락/거부 API
    // URL: /api/v1/offers/users/{senderId}/status
    @PatchMapping("/users/{senderId}/status")
    public ApiResponse<OfferAcceptRes> updateOfferStatus(
            @AuthenticationPrincipal String userId,
            @PathVariable Long senderId,
            @RequestBody OfferStatusReq req
    ) {
        Long receiverId = Long.parseLong(userId);

        OfferAcceptRes result = offerService.updateOfferStatus(receiverId, senderId, req);

        if (result != null) {
            return ApiResponse.onSuccess(result);
        } else {
            return ApiResponse.onSuccess(null);
        }
    }
}