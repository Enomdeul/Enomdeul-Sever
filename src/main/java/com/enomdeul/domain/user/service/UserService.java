package com.enomdeul.domain.user.service;

import com.enomdeul.domain.user.dto.request.UserLoginReq;
import com.enomdeul.domain.user.dto.request.UserSignupReq;
import com.enomdeul.domain.user.dto.response.UserLoginRes;
import com.enomdeul.domain.user.dto.response.UserSignupRes;
import com.enomdeul.domain.user.entity.User;
import com.enomdeul.domain.user.exception.UserErrorCode; // ★
import com.enomdeul.domain.user.exception.UserException; // ★
import com.enomdeul.domain.user.repository.UserRepository;
import com.enomdeul.global.config.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserSignupRes signup(UserSignupReq req) {
        // 회원가입 시 중복 체크 로직도 추가하면 좋습니다 (선택 사항)
        if (userRepository.existsByLoginId(req.getLoginId())) {
            throw new UserException(UserErrorCode.DUPLICATE_LOGIN_ID);
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new UserException(UserErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(req.getPassword());

        User newUser = User.builder()
                .loginId(req.getLoginId())
                .password(encodedPassword)
                .email(req.getEmail())
                .name(req.getLoginId())
                .build();

        User savedUser = userRepository.save(newUser);
        return UserSignupRes.from(savedUser);
    }

    // 로그인
    public UserLoginRes login(UserLoginReq req) {
        // 1. 아이디로 유저 찾기
        User user = userRepository.findByLoginId(req.getId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND)); // ★ 예외 변경

        // 2. 비밀번호 확인
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new UserException(UserErrorCode.PASSWORD_NOT_MATCH); // ★ 예외 변경
        }

        // 3. 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId());

        return UserLoginRes.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}