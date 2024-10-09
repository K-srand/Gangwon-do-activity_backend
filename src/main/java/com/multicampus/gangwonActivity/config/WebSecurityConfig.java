package com.multicampus.gangwonActivity.config;


import com.multicampus.gangwonActivity.filter.JwtAuthenticationFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final DefaultOAuth2UserService oAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource())
                )
                .csrf(CsrfConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)) // 세션이 항상 생성되도록 설정
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/",
                                "/api/v1/auth/**",
                                "/api/v1/search/**",
                                "/api/v1/board/**",
                                "/file/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/api/v1/mypage/**",
                                "/swagger-ui.html",
                                "/api/v1/getjson/**",
                                "/api/v1/auth/check-id",
                                "/api/v1/auth/check-nickname",
                                "/api/v1/**",
                                "/api/v1/weather/**",
                                "/resources/**",
                                "/static/**",
                                "/api/v1/admin/**",
                                "/oauth2/**"
                                ).permitAll()
                        .requestMatchers("/api/v1/user/**"
                        ).hasRole("USER")
//                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                        .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new FailedAuthenticationEntryPoint())
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }

    //CORS 정책허용
    @Bean
    protected CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://gangwonactivity.site/")); // 요청을 보낼 출처 설정
        corsConfiguration.setAllowedMethods(Arrays.asList("HEAD", "PUT", "POST", "GET", "OPTIONS", "DELETE", "PATCH")); // 허용할 HTTP 메서드 설정
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // 허용할 요청 헤더 설정
        corsConfiguration.setAllowCredentials(true); // 자격 증명 허용 설정
        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization", "Authorization-refresh")); // 노출할 응답 헤더 설정
        corsConfiguration.setMaxAge(3600L); // pre-flight 요청 캐시 시간 설정

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        //다른 정책 만들고 싶으면 위에 corsConfiguration하나 더 생성하고
//        source.registerCorsConfiguration("api/v2/**", corsConfiguration);
        return source;
    }

    class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"code\":\"NP\", \"message\": \"Do not have permission.\"}");

        }
    }


}
