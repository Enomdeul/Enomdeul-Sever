package com.enomdeul.domain.offer.controller.docs;

import com.enomdeul.domain.offer.dto.request.OfferRequest;
import com.enomdeul.domain.offer.dto.response.OfferResponse;
import com.enomdeul.domain.offer.dto.response.ReceivedOfferRes;
import com.enomdeul.domain.offer.dto.response.SentOfferRes;
import com.enomdeul.domain.offer.exception.OfferErrorCode;
import com.enomdeul.domain.skill.exception.SkillErrorCode;
import com.enomdeul.domain.user.exception.UserErrorCode;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiErrorCode;
import com.enomdeul.global.exception.swagger.ApiErrorCodeExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Offer", description = "오퍼, 매칭 관련 API")
public interface OfferControllerDocs {

    @Operation(summary = "오퍼 생성", description = "오퍼를 생성합니다.")
    @ApiErrorCodeExamples(
            value = {OfferErrorCode.class, UserErrorCode.class, ApiErrorCode.class},
            include = { "INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR", "OFFER_NOT_FOUND", "USER_NOT_FOUND"})
    ApiResponse<OfferResponse> sendOffer(@AuthenticationPrincipal String userId, @RequestBody OfferRequest request);

    @Operation(summary = "받은 오퍼 목록 조회", description = "받은 오퍼 목록을 조회합니다.")
    @ApiErrorCodeExamples(
            value = {SkillErrorCode.class, UserErrorCode.class, ApiErrorCode.class},
            include = { "INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR"})
    ApiResponse<List<ReceivedOfferRes>> getReceivedOffers(@AuthenticationPrincipal String userId);

    @Operation(summary = "보낸 오퍼 목록 조회", description = "보낸 오퍼 목록을 조회합니다.")
    @ApiErrorCodeExamples(
            value = {ApiErrorCode.class},
            include = { "INTERNAL_SERVER_ERROR", "FORBIDDEN_ERROR"})
    public ApiResponse<List<SentOfferRes>> getSentOffers(@AuthenticationPrincipal String userId);

}
