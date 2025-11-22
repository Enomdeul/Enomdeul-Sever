package com.enomdeul.domain.card;

import com.enomdeul.domain.card.docs.CardControllerDocs;
import com.enomdeul.domain.card.dto.request.UserCardReq;
import com.enomdeul.domain.card.dto.response.UserCardRes;
import com.enomdeul.domain.card.service.CardService;
import com.enomdeul.global.common.response.ApiResponse;
import com.enomdeul.global.common.response.code.ApiSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController implements CardControllerDocs {

    private final CardService cardService;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createUserCard(@AuthenticationPrincipal String id, @Valid @RequestBody UserCardReq request) {
        Long userId = Long.parseLong(id);
        cardService.createUserCard(userId, request);

        return ResponseEntity.ok(ApiResponse.of(ApiSuccessCode.SUCCESS));
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserCardRes>> findUserCard(@AuthenticationPrincipal String id) {
        Long userId = Long.parseLong(id);
        UserCardRes response = cardService.findUserCard(userId);

        return ResponseEntity.ok(ApiResponse.of(ApiSuccessCode.SUCCESS, response));
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserCardRes>> findUserCard(@PathVariable Long userId) {
        UserCardRes response = cardService.findUserCard(userId);

        return ResponseEntity.ok(ApiResponse.of(ApiSuccessCode.SUCCESS, response));
    }
}