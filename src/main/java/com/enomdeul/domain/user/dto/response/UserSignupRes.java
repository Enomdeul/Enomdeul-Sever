package com.enomdeul.domain.user.dto.response;

import com.enomdeul.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserSignupRes {
    private Long userId;
    private String loginId;
    private String email;

    public static UserSignupRes from(User user) {
        return UserSignupRes.builder()
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .email(user.getEmail())
                .build();
    }
}