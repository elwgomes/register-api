package com.elwgomes.cadastro.api.security.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.elwgomes.cadastro.api.dto.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getHeader("Authorization") != null) {
			Authentication auth = TokenUtil.decodeToken(request);
			if (auth != null)
				SecurityContextHolder.getContext().setAuthentication(auth);
			else {
				ErrorDTO erro = new ErrorDTO(401,"Invalid token.");
				response.setStatus(erro.getStatus());
				response.setContentType("application/json");
				ObjectMapper MAPPER = new ObjectMapper();
				response.getWriter().print(MAPPER.writeValueAsString(erro));
				response.getWriter().flush();
				return;
			}
		}
		filterChain.doFilter(request, response);
    }

}
