package com.elwgomes.cadastro.api.controllers;

import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.services.UserService;
import com.elwgomes.cadastro.api.services.ViaCepService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private ViaCepService viaCepService;


    @GetMapping
    public ResponseEntity<List<User>> findAll () {
	    return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById (@PathVariable("id")  Long id) {
        return ResponseEntity
                .ok()
                .body(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> insertUser (@RequestBody User user) throws Exception {
        userService.insertUser(user);
        return ResponseEntity.ok("New user added!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully!");
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateData (@PathVariable Long id, @RequestBody User user) throws Exception {
        return ResponseEntity
                .ok()
                .body(user = userService.updateUser(id, user));
    }

}
