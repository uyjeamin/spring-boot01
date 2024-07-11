package com.example.springboot01.security.jwt;

import com.example.springboot01.security.jwt.FilterConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return
                http
                        .csrf(CsrfConfigurer::disable)
                        .cors(Customizer.withDefaults())
                        .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(authorize ->
                                authorize
                                        // user
                                        .requestMatchers(HttpMethod.POST, "/api/user/signup").permitAll()

                                        // auth
                                        .requestMatchers("/auth/token").permitAll()

                                        // feed
                                        .requestMatchers(HttpMethod.GET, "/feed/list").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/feed/{feed-id}").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/feed/search/{title}").permitAll()

                                        .anyRequest().authenticated()
                        ).apply(new FilterConfig(jwtTokenProvider, objectMapper))
                        .and()
                        .build();
    } 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
