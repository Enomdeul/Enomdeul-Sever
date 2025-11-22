package com.enomdeul.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserLoginRes {
    private String accessToken;
    private String refreshToken;
}