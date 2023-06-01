package com.elwgomes.cadastro.api.security;


import com.elwgomes.cadastro.api.security.config.JWTAuthFilter;
import com.elwgomes.cadastro.api.security.config.UserAuthenticationEntryPoint;
import com.elwgomes.cadastro.api.security.config.UserAuthenticationProvider;
import com.elwgomes.cadastro.api.security.config.UsernamePasswordAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;

    public SecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint, UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(userAuthenticationEntryPoint);
        http
                .addFilterBefore(
                        new UsernamePasswordAuthFilter(userAuthenticationProvider),
                        BasicAuthenticationFilter.class
                )
                .addFilterBefore(
                        new JWTAuthFilter(userAuthenticationProvider),
                        UsernamePasswordAuthFilter.class
                )
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
        }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
}

    }