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
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        User receiver = userRepository.findById(request.getOffereeId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Offer offer = Offer.builder()
                .offerer(sender)
                .offeree(receiver)
                .offerStatus(OfferStatus.WAITING)
                .build();

        offerRepository.save(offer);
        return OfferResponse.from(offer);
    }

    @Transactional
    public OfferAcceptRes updateOfferStatus(Long receiverId, Long offererId, OfferStatusReq req) {
        // 1. 복합키로 오퍼 조회 (보낸 사람 offererId, 받는 사람 receiverId)
        OfferId offerId = new OfferId(offererId, receiverId);

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new OfferException(OfferErrorCode.OFFER_NOT_FOUND));

        // 2. 상태 변경 (Dirty Checking)
        offer.changeStatus(req.getStatus());

        // 3. 수락(ACCEPTED)인 경우에만 상대방 이메일 반환
        if (req.getStatus() == OfferStatus.ACCEPTED) {
            return OfferAcceptRes.builder()
                    .email(offer.getOfferer().getEmail())
                    .build();
        }

        return null;
    }

    public List<ReceivedOfferRes> getReceivedOffers(Long receiverId) {
        List<Offer> offers = offerRepository.findAllReceivedOffers(receiverId);

        return offers.stream()
                .map(ReceivedOfferRes::from)
                .collect(Collectors.toList());
    }

    public List<SentOfferRes> getSentOffers(Long senderId) {
        List<Offer> offers = offerRepository.findAllSentOffers(senderId);
        return offers.stream().map(SentOfferRes::from).collect(Collectors.toList());
    }
}