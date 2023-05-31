package com.elwgomes.cadastro.api.services;

import com.elwgomes.cadastro.api.entities.Address;
import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(UUID id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public User insertUser(User user) throws Exception {
        viaCepService.fetchAddressFromViaCep(user);
        Address address = user.getAddress();
        address.setUser(user);
        user.setAddress(address);
        return userRepository.save(user);
    }

    @Transactional
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User updateCep(UUID id, User updatedUser) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // update data
        // check if cep was changed
        if (!user.getCep().equals(updatedUser.getCep())) {
            // update cep and get new address
            user.setCep(updatedUser.getCep());
            viaCepService.fetchAddressFromViaCep(user);
            userRepository.save(user);
        }

        return userRepository.save(user);
    }
}
