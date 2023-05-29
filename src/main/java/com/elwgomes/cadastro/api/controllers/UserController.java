package com.elwgomes.cadastro.api.controllers;

import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.services.UserService;
import com.elwgomes.cadastro.api.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class UserController{

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll () throws Exception {
	    return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById (@PathVariable("id")  Long id) {
        return ResponseEntity
                .ok()
                .body(userService.findById(id));
    }
}
