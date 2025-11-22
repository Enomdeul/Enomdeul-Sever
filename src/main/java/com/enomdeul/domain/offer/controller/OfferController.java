package com.enomdeul.domain.offer.controller;

import com.enomdeul.domain.offer.dto.request.OfferRequest;
import com.enomdeul.domain.offer.dto.request.OfferStatusReq;
import com.enomdeul.domain.offer.dto.response.OfferAcceptRes;
import com.enomdeul.domain.offer.dto.response.OfferResponse;
import com.enomdeul.domain.offer.dto.response.ReceivedOfferRes;
import com.enomdeul.domain.offer.dto.response.SentOfferRes;
import com.enomdeul.domain.offer.service.OfferService;
import com.enomdeul.global.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offers")
public class OfferController {

    private final OfferService offerService;

    /**
     * 1. 매칭 오퍼 전송 API
     * [POST] /api/v1/offers
     */
    @PostMapping
    public ApiResponse<OfferResponse> sendOffer(
            @AuthenticationPrincipal String userId,
            @RequestBody @Valid OfferRequest request
    ) {
        Long senderId = Long.parseLong(userId);
        OfferResponse response = offerService.sendOffer(senderId, request);
        return ApiResponse.onSuccess(response);
    }

    /**
     * 2. 받은 오퍼 목록 조회 API
     * [GET] /api/v1/offers/received
     */
    @GetMapping("/received")
    public ApiResponse<List<ReceivedOfferRes>> getReceivedOffers(
            @AuthenticationPrincipal String userId
    ) {
        Long receiverId = Long.parseLong(userId);
        List<ReceivedOfferRes> response = offerService.getReceivedOffers(receiverId);
        return ApiResponse.onSuccess(response);
    }

    /**
     * 3. 보낸 오퍼 목록 조회 API
     * [GET] /api/v1/offers/sent
     */
    @GetMapping("/sent")
    public ApiResponse<List<SentOfferRes>> getSentOffers(
            @AuthenticationPrincipal String userId
    ) {
        Long senderId = Long.parseLong(userId);
        List<SentOfferRes> response = offerService.getSentOffers(senderId);
        return ApiResponse.onSuccess(response);
    }

    /**
     * 4. 받은 매칭 오퍼 수락/거부 API
     * [PATCH] /api/v1/offers/users/{senderId}/status
     */
    @PatchMapping("/users/{senderId}/status")
    public ApiResponse<OfferAcceptRes> updateOfferStatus(
            @AuthenticationPrincipal String userId, // 내 ID (Receiver)
            @PathVariable Long senderId,            // 상대방 ID (Sender)
            @RequestBody @Valid OfferStatusReq req
    ) {
        Long receiverId = Long.parseLong(userId);

        OfferAcceptRes result = offerService.updateOfferStatus(receiverId, senderId, req);

        // 수락 시 결과 반환, 거절 시 null 반환 (성공 메시지는 동일)
        return ApiResponse.onSuccess(result);
    }
}