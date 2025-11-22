package com.enomdeul.domain.user.dto.response;

public record CheckLoginIdRes(
        String login_id,
        boolean available
){
}
