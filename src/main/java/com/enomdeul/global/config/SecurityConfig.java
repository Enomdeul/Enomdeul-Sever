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
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    import java.util.List;

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

                            // 기존 허용 경로들
                            .requestMatchers("/api/v1/auth/**", "/api/v1/samples", "/api/v1/skills").permitAll()

                            // 그 외 모든 요청은 인증 필요
                            .anyRequest().authenticated()
                    );

            return http.build();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();

            // 모든 출처 허용 (프론트엔드 포트가 뭐든 상관없게)
            configuration.setAllowedOriginPatterns(List.of("*"));

            // 모든 HTTP 메서드 허용 (GET, POST, PUT, DELETE, PATCH, OPTIONS)
            configuration.setAllowedMethods(List.of("*"));

            // 모든 헤더 허용
            configuration.setAllowedHeaders(List.of("*"));

            // 자격 증명 허용 (쿠키, Authorization 헤더 등)
            configuration.setAllowCredentials(true);

            // 프론트에서 Authorization 헤더를 읽을 수 있게 노출
            configuration.setExposedHeaders(List.of("Authorization"));

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }