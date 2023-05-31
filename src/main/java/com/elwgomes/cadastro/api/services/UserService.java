package com.elwgomes.cadastro.api.services;

import com.elwgomes.cadastro.api.entities.Address;
import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public User insertUser(User user) throws Exception {
        viaCepService.fetchAddressFromViaCep(user);
        Address address = user.getAddress();
        address.setUser(user);
        user.setAddress(address);
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // update data
        user.setFirstname(updatedUser.getFirstname());
        user.setLastname(updatedUser.getLastname());
        user.setPassword(updatedUser.getPassword());

        // check if cep was changed
        if (!user.getCep().equals(updatedUser.getCep())) {
            // Atualizar o CEP e obter o novo endereço
            user.setCep(updatedUser.getCep());
            viaCepService.fetchAddressFromViaCep(user);
            userRepository.save(user);
        }

        return userRepository.save(user);
    }
}
