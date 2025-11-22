package com.enomdeul.domain.card;

import com.enomdeul.domain.card.docs.CardControllerDocs;
import com.enomdeul.domain.card.dto.UserCardReq;
import com.enomdeul.domain.card.service.CardService;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController implements CardControllerDocs {

    private final CardService cardService;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createUserCard(@AuthenticationPrincipal String id, @RequestBody UserCardReq request) {
        Long userId = Long.parseLong(id);
        cardService.createUserCard(userId, request);

        return ResponseEntity.ok(ApiResponse.of(ApiSuccessCode.SUCCESS));
    }
}