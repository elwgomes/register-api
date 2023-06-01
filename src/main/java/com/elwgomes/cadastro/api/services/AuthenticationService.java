package com.elwgomes.cadastro.api.services;

import java.nio.CharBuffer;

import com.elwgomes.cadastro.api.dto.CredentialsDTO;
import com.elwgomes.cadastro.api.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO authenticate(CredentialsDTO credentialsDto) {
        String encodedMasterPassword = passwordEncoder.encode(CharBuffer.wrap("s3nh@"));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), encodedMasterPassword)) {
            return new UserDTO("elwgomes", "s3nh@", "token");
        }
        throw new RuntimeException("Invalid password");
    }

    public UserDTO findByUsername(String username) {
        if ("username".equals(username)) {
            return new UserDTO("elwgomes", "s3nh@", "token");
        }
        throw new RuntimeException("Invalid login");
    }
}