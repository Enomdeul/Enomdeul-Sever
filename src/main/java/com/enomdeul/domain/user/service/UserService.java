package com.enomdeul.domain.user.service;

import com.enomdeul.domain.user.dto.request.UserLoginReq;
import com.enomdeul.domain.user.dto.request.UserSignupReq;
import com.enomdeul.domain.user.dto.response.UserLoginRes;
import com.enomdeul.domain.user.dto.response.UserSignupRes;
import com.enomdeul.domain.user.entity.User;
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
    // ★ 아래 줄을 추가해야 합니다!
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserSignupRes signup(UserSignupReq req) {

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

    // 로그인
    public UserLoginRes login(UserLoginReq req) {
        // 1. 아이디로 유저 찾기
        User user = userRepository.findByLoginId(req.getId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 아이디입니다."));

        // 2. 비밀번호 확인 (입력된 비번 vs DB에 저장된 비번 비교)
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 토큰 생성 (Access + Refresh)
        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId());

        // 4. 결과 반환
        return UserLoginRes.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}