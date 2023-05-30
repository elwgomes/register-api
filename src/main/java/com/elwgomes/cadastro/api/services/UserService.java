package com.elwgomes.cadastro.api.services;

import com.elwgomes.cadastro.api.entities.Address;
import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ViaCepService viaCepService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

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
    public User update(Long id, User obj) throws Exception {
        User user = userRepository.getReferenceById(id);
        updateData(user, obj);
        return userRepository.save(user);
    }

    private void updateData(User user, User obj) throws Exception {
        // data to be updated
        user.setFirstname(obj.getFirstname());
        user.setLastname(obj.getLastname());
        user.setPassword(obj.getPassword());

        if (!user.getCep().equals(obj.getCep())) {
            user.setCep(obj.getCep());
            viaCepService.fetchAddressFromViaCep(obj);
            Address address = obj.getAddress();
            address.setUser(obj);
            obj.setAddress(address);
        }
    }
}
