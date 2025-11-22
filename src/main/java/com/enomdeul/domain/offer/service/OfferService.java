package com.enomdeul.domain.offer.service;

import com.enomdeul.domain.offer.dto.request.OfferRequest;
import com.enomdeul.domain.offer.dto.request.OfferStatusReq;
import com.enomdeul.domain.offer.dto.response.OfferAcceptRes;
import com.enomdeul.domain.offer.dto.response.OfferResponse;
import com.enomdeul.domain.offer.dto.response.ReceivedOfferRes;
import com.enomdeul.domain.offer.dto.response.SentOfferRes;
import com.enomdeul.domain.offer.entity.Offer;
import com.enomdeul.domain.offer.entity.OfferId;
import com.enomdeul.domain.offer.entity.OfferStatus;
import com.enomdeul.domain.offer.exception.OfferErrorCode;
import com.enomdeul.domain.offer.exception.OfferException;
import com.enomdeul.domain.offer.repository.OfferRepository;
import com.enomdeul.domain.user.entity.User;
import com.enomdeul.domain.user.exception.UserErrorCode;
import com.enomdeul.domain.user.exception.UserException;
import com.enomdeul.domain.user.repository.UserRepository;
import com.enomdeul.global.exception.GlobalErrorCode;
import com.enomdeul.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;

    /**
     * 1. 오퍼 전송 (보내기)
     */
    @Transactional
    public OfferResponse sendOffer(Long senderId, OfferRequest request) {
        // 1-1. 자기 자신에게 보내는지 확인
        if (senderId.equals(request.getOffereeId())) {
            throw new OfferException(OfferErrorCode.CANNOT_OFFER_SELF);
        }

        // 1-2. 보낸 사람(나) 존재 확인
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        // 1-3. 받는 사람(상대방) 존재 확인
        User receiver = userRepository.findById(request.getOffereeId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        // 1-4. 이미 보낸 오퍼인지 확인 (중복 방지)
        if (offerRepository.existsByOffererAndOfferee(sender, receiver)) {
            throw new OfferException(OfferErrorCode.ALREADY_SENT_OFFER);
        }

        // 1-5. 오퍼 생성 및 저장 (초기 상태: WAITING)
        Offer offer = Offer.builder()
                .offerer(sender)
                .offeree(receiver)
                .offerStatus(OfferStatus.WAITING)
                .build();

        offerRepository.save(offer);
        return OfferResponse.from(offer);
    }

    /**
     * 2. 오퍼 상태 변경 (수락/거절)
     */
    @Transactional
    public OfferAcceptRes updateOfferStatus(Long receiverId, Long senderId, OfferStatusReq req) {
        // 2-1. 유저 존재 확인 (나, 상대방 모두 확인)
        if (!userRepository.existsById(receiverId) || !userRepository.existsById(senderId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        // 2-2. 오퍼 존재 확인 (복합키 사용)
        OfferId offerId = new OfferId(senderId, receiverId);
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new OfferException(OfferErrorCode.OFFER_NOT_FOUND));

        // 2-3. 이미 처리된 오퍼인지 확인 (WAITING이 아니면 예외)
        if (offer.getOfferStatus() != OfferStatus.WAITING) {
            throw new OfferException(OfferErrorCode.ALREADY_PROCESSED_OFFER);
        }

        // 2-4. 상태 변경 (Dirty Checking)
        offer.changeStatus(req.getStatus());

        // 2-5. 수락(ACCEPTED)인 경우에만 이메일 반환
        if (req.getStatus() == OfferStatus.ACCEPTED) {
            return OfferAcceptRes.builder()
                    .email(offer.getOfferer().getEmail())
                    .build();
        }

        return null; // 거절 시 null 반환
    }

    /**
     * 3. 받은 오퍼 목록 조회
     */
    public List<ReceivedOfferRes> getReceivedOffers(Long receiverId) {
        // 3-1. 유저 존재 확인
        if (!userRepository.existsById(receiverId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        // 3-2. 목록 조회
        List<Offer> offers = offerRepository.findAllReceivedOffers(receiverId);

        // 3-3. 목록 없음 예외 처리
        if (offers.isEmpty()) {
            throw new GlobalException(GlobalErrorCode.OFFER_LIST_EMPTY);
        }

        return offers.stream()
                .map(ReceivedOfferRes::from)
                .collect(Collectors.toList());
    }

    /**
     * 4. 보낸 오퍼 목록 조회
     */
    public List<SentOfferRes> getSentOffers(Long senderId) {
        // 4-1. 유저 존재 확인
        if (!userRepository.existsById(senderId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        // 4-2. 목록 조회
        List<Offer> offers = offerRepository.findAllSentOffers(senderId);

        // 4-3. 목록 없음 예외 처리
        if (offers.isEmpty()) {
            throw new GlobalException(GlobalErrorCode.SENT_OFFER_LIST_EMPTY);
        }

        return offers.stream()
                .map(SentOfferRes::from)
                .collect(Collectors.toList());
    }
}