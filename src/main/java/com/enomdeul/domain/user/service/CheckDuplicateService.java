package com.enomdeul.domain.user.service;

import com.enomdeul.domain.user.dto.response.CheckEmailRes;
import com.enomdeul.domain.user.dto.response.CheckLoginIdRes;
import com.enomdeul.domain.user.exception.UserErrorCode;
import com.enomdeul.domain.user.exception.UserException;
import com.enomdeul.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class CheckDuplicateService {
    private final UserRepository userRepository;

    public CheckLoginIdRes isLoginIdAvailable(String loginId){
        if(userRepository.existsByLoginId(loginId)){
            throw new UserException(UserErrorCode.DUPLICATE_LOGIN_ID);
        }

        return new CheckLoginIdRes(loginId,true);
    }

    public CheckEmailRes isEmailAvailable(String email){
        if(userRepository.existsByEmail(email)){
            throw new UserException(UserErrorCode.DUPLICATE_EMAIL);
        }

        return new CheckEmailRes(email, true);
    }
}
