package com.elwgomes.cadastro.api.services;

import com.elwgomes.cadastro.api.entities.Address;
import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.repositories.AddressRepository;
import com.elwgomes.cadastro.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll () {
        return addressRepository.findAll();
    }

    public Address findById (Long id) {
        return addressRepository.findById(id).get();
    }
}
