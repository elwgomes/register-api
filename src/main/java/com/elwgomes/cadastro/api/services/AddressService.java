package com.elwgomes.cadastro.api.services;

import com.elwgomes.cadastro.api.entities.Address;
import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.repositories.AddressRepository;
import com.elwgomes.cadastro.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public List<Address> findAll () {
        return addressRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Address findById (UUID id) {
        return addressRepository.findById(id).get();
    }
}
