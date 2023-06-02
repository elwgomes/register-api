package com.elwgomes.cadastro.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.elwgomes.cadastro.api.security.config.MyFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

	http
			.authorizeHttpRequests(authConfig -> {
			authConfig.requestMatchers(HttpMethod.GET, "/public").permitAll();
			authConfig.requestMatchers(HttpMethod.POST, "/login").permitAll();
			authConfig.anyRequest().authenticated();
			})
			.csrf().disable()
			.cors();

	http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);

	return http.build();

    }
}
