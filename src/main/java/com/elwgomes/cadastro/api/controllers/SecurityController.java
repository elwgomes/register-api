package com.elwgomes.cadastro.api.controllers;

import com.elwgomes.cadastro.api.dto.UserDTO;
import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.entities.Usuario;
import com.elwgomes.cadastro.api.security.config.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elwgomes.cadastro.api.security.config.AuthToken;

@RestController
public class SecurityController {


    @GetMapping("/public")
    String publicEndpoint () {
	return "This is a free endpoint.";
    }

    @GetMapping("/private")
    String privateEndpoint () {
	return "Welcome! This is a private endpoint.";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthToken> realizarLogin (@RequestBody UserDTO userDTO) {
	    if (userDTO.getUsername().equals("elwgomes") && userDTO.getPassword().equals("s3nh@")) {
    	    return ResponseEntity.ok(TokenUtil.encodeToken(userDTO));
	    }
        return ResponseEntity.status(401).build();
    }
}
