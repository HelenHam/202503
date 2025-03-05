package com.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
//        List<String> originUris = oAuthClientService.getOriginUris();
        List<String> originUris = List.of("http://d.0neteam.co.kr", "http://127.0.0.1:5173", "http://localhost:5173");
        originUris.forEach(config::addAllowedOrigin);
//        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("GET");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        // CORS 활성화
        http.cors(cors -> cors.configurationSource(request -> {
            var corsConfig = new org.springframework.web.cors.CorsConfiguration();
            corsConfig.addAllowedOrigin("http://localhost:5173"); // React 클라이언트 URL 설정
            corsConfig.addAllowedMethod("POST");
            corsConfig.addAllowedMethod("GET");
            //corsConfig.addAllowedMethod("PUT");
            //corsConfig.addAllowedMethod("DELETE");
            corsConfig.addAllowedHeader("*");  // 모든 헤더 허용
            corsConfig.setAllowCredentials(true);  // 쿠키 포함 요청 허용
            return corsConfig;
        }));


        http.authorizeHttpRequests( req -> {
            req.anyRequest().permitAll();

        });

        return http.build();
    }

}
