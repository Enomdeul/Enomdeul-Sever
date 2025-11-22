package com.enomdeul.domain.user.service;

import com.enomdeul.domain.user.dto.request.UserSignupReq;
import com.enomdeul.domain.user.dto.response.UserSignupRes;
import com.enomdeul.domain.user.entity.User;
import com.enomdeul.domain.user.exception.UserErrorCode;
import com.enomdeul.domain.user.exception.UserException;
import com.enomdeul.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSignupRes signup(UserSignupReq req) {
        // 1. 중복 체크 (걸리면 커스텀 예외인 UserException 발생)
        if (userRepository.existsByLoginId(req.getLoginId())) {
            throw new UserException(UserErrorCode.DUPLICATE_LOGIN_ID);
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new UserException(UserErrorCode.DUPLICATE_EMAIL);
        }

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(req.getPassword());

        // 3. 엔티티 생성 (빌더 패턴)
        User newUser = User.builder()
                .loginId(req.getLoginId())
                .password(encodedPassword)
                .email(req.getEmail())
                // 화면에 이름 입력이 없으므로 아이디를 이름으로 대체하거나 null 처리
                .name(req.getLoginId())
                .build();

        // 4. 저장 및 반환
        User savedUser = userRepository.save(newUser);
        return UserSignupRes.from(savedUser);
    }
}