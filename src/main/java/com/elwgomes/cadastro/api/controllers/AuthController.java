package com.elwgomes.cadastro.api.controllers;

import com.elwgomes.cadastro.api.dto.UserDTO;
import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.repositories.UserRepository;
import com.elwgomes.cadastro.api.security.config.TokenUtil;
import com.elwgomes.cadastro.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elwgomes.cadastro.api.security.config.AuthToken;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthToken> login (@RequestBody UserDTO userDTO) {

        User user = userRepository.findByUsername(userDTO.getUsername());

        if (user != null
                && user.getUsername().equals(userDTO.getUsername())
                && user.getPassword().equals(userDTO.getPassword())) {
            AuthToken authToken = TokenUtil.encodeToken(userDTO);
            return ResponseEntity.ok(authToken);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> insertUser (@RequestBody User user) throws Exception {
        userService.insertUser(user);
        String message = "New user added! \nUsername: " + user.getUsername() + "\nPassword: " + user.getPassword();
        return ResponseEntity.ok(message);
    }

}
