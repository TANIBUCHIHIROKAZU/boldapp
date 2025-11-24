package com.example.boldapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	// H2 Console configuration
    	 http
         .authorizeHttpRequests(auth -> auth
                 .requestMatchers("/h2-console/**").permitAll()
                 .requestMatchers("/login/**").permitAll()
                 .anyRequest().authenticated()
         )
         .csrf(csrf -> csrf
                 .ignoringRequestMatchers("/h2-console/**")
         )
         .headers(headers -> headers
                 .frameOptions(frameOptions -> frameOptions.disable())
         )
         .formLogin(form -> form
        		 .loginPage("/login")         // ログインページ
                 .loginProcessingUrl("/login") // 認証処理のURL
                 .defaultSuccessUrl("/", true) // ログイン成功後
                 .permitAll()
         )
    	 .logout(logout -> logout
                 .logoutUrl("/logout")
                 .logoutSuccessUrl("/login?logout")
             );
        return http.build();
    }
    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new Pbkdf2PasswordEncoder();
//    }
}
