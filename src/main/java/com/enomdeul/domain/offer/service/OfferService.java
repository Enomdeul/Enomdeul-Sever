package com.enomdeul.domain.offer.service;

import com.enomdeul.domain.offer.dto.request.OfferRequest;
import com.enomdeul.domain.offer.dto.response.OfferResponse;
import com.enomdeul.domain.offer.dto.response.ReceivedOfferRes;
import com.enomdeul.domain.offer.dto.response.SentOfferRes;
import com.enomdeul.domain.offer.entity.Offer;
import com.enomdeul.domain.offer.repository.OfferRepository;
import com.enomdeul.domain.user.entity.User;
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

    @Transactional
    public OfferResponse sendOffer(Long senderId, OfferRequest request) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND_ERROR));

        User receiver = userRepository.findById(request.getOffereeId())
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.MEMBER_NOT_FOUND_ERROR));

        Offer offer = Offer.builder()
                .offerer(sender)
                .offeree(receiver)
                .offerStatus(true)
                .build();

        offerRepository.save(offer);
        return OfferResponse.from(offer);
    }

    public List<ReceivedOfferRes> getReceivedOffers(Long receiverId) {
        List<Offer> offers = offerRepository.findAllReceivedOffers(receiverId);

        if (offers.isEmpty()) {
            throw new GlobalException(GlobalErrorCode.OFFER_LIST_EMPTY);
        }

        return offers.stream()
                .map(ReceivedOfferRes::from)
                .collect(Collectors.toList());
    }

    public List<SentOfferRes> getSentOffers(Long senderId) {
        // 1. 내가 보낸 오퍼 리스트 조회 (받는 사람 정보 포함)
        List<Offer> offers = offerRepository.findAllSentOffers(senderId);

        // 2. 목록이 0개일 때 에러 처리 (명세서: 보낸 오퍼 목록 없음)
        if (offers.isEmpty()) {
            throw new GlobalException(GlobalErrorCode.SENT_OFFER_LIST_EMPTY);
        }

        // 3. DTO 변환
        return offers.stream()
                .map(SentOfferRes::from)
                .collect(Collectors.toList());
    }
}