package com.elwgomes.cadastro.api.controllers;

import com.elwgomes.cadastro.api.entities.Address;
import com.elwgomes.cadastro.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/address")
    public ResponseEntity<List<Address>> findAll () throws Exception {
	    return ResponseEntity.ok().body(addressRepository.findAll());
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Optional> findById (@PathVariable("id")  Long id) {
        Optional obj = addressRepository.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
