package com.enomdeul.domain.offer.service;

import com.enomdeul.domain.offer.dto.request.OfferRequest;
import com.enomdeul.domain.offer.dto.response.OfferResponse;
import com.enomdeul.domain.offer.entity.Offer;
import com.enomdeul.domain.offer.repository.OfferRepository;
import com.enomdeul.domain.user.entity.User;
import com.enomdeul.domain.user.repository.UserRepository;
import com.enomdeul.global.exception.GlobalErrorCode;
import com.enomdeul.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;

    @Transactional
    public OfferResponse sendOffer(Long senderId, OfferRequest request) {
        // 1. 오퍼를 보낸 사람 (로그인 사용자) 조회
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND_ERROR));

        // 2. 오퍼를 받을 사람 조회
        User receiver = userRepository.findById(request.getOffereeId())
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND_ERROR));

        // 3. 오퍼 엔티티 생성 (초기 상태는 true 등으로 설정, 요구사항에 따라 변경)
        Offer offer = Offer.builder()
                .offerer(sender)
                .offeree(receiver)
                .offerStatus(true) // 혹은 대기 상태라면 null/false 등 정책에 맞게 설정
                .build();

        // 4. 저장
        offerRepository.save(offer);

        // 5. 응답 DTO 반환
        return OfferResponse.from(offer);
    }
}