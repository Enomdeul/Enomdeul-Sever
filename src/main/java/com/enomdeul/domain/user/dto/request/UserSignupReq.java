package com.enomdeul.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignupReq {
    private String loginId;
    private String password;
    private String email;
}