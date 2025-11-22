    package com.enomdeul.global.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        // 1. 비밀번호 암호화 빈 등록
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    // 2. CSRF 해제 (JWT 사용 시 불필요)
                    .csrf(AbstractHttpConfigurer::disable)

                    // 3. Form 로그인, Basic Http 비활성화 (REST API이므로)
                    .formLogin(AbstractHttpConfigurer::disable)
                    .httpBasic(AbstractHttpConfigurer::disable)

                    // 4. 세션 사용 안 함 (JWT 사용 시 STATELESS 설정 필수)
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )

                    // 5. 경로별 인가 설정
                    .authorizeHttpRequests(auth -> auth
                            // Swagger 관련 경로 허용
                            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                            // 정적 리소스 허용
                            .requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.ico").permitAll()

                            // 회원가입/로그인 API 허용 (아까 만든 UserController 경로)
                            .requestMatchers("/api/users/**").permitAll()

                            // 기존 허용 경로들
                            .requestMatchers("/api/v1/auth/**", "/login/**", "/oauth2/**", "/api/v1/samples").permitAll()

                            // 그 외 모든 요청은 인증 필요
                            .anyRequest().authenticated()
                    );

            // 추후 JWT Filter 추가 필요
            // .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }
    }