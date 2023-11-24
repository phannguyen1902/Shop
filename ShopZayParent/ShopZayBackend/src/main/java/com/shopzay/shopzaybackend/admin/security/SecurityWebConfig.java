package com.shopzay.shopzaybackend.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/resources/dist/**","/resources/plugins/**")
                                .permitAll() // Cho phép truy cập các tài nguyên tĩnh trong các thư mục tương ứng
                                .anyRequest()
                                .permitAll()
                                //.authenticated()
                )
                .formLogin(loginConfigurer -> {
                    loginConfigurer
                            .loginPage("/login") // URL của trang login tùy chỉnh
                            .failureUrl("/custom-login?error=true") // URL khi đăng nhập thất bại
                            .permitAll(); // Cho phép tất cả người dùng truy cập trang login
                });

        return http.build();
    }



}
