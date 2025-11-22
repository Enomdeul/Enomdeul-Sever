package com.enomdeul.domain.user.dto.response;

public record CheckEmailRes(
        String email,
        boolean available
) {
}
