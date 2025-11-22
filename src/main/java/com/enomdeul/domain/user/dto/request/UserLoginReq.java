package com.enomdeul.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginReq {
    private String id; // 명세서의 "아이디" (실제 DB 컬럼은 loginId)
    private String password;
}