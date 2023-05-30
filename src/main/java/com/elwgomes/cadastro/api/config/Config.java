package com.elwgomes.cadastro.api.config;

import com.elwgomes.cadastro.api.entities.Address;
import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.repositories.UserRepository;
import com.elwgomes.cadastro.api.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class Config implements CommandLineRunner {

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User();
        u1.setUsername("elyssana");
        u1.setEmail("ely@ely.com");
        u1.setCep("57075420");
        u1.setFirstname("Elyssana");
        u1.setLastname("Silva");
        u1.setPassword("s3nh@");

        User u2 = new User();
        u2.setUsername("elwgomes");
        u2.setEmail("leonardo@leonardo.com");
        u2.setCep("57045300");
        u2.setFirstname("Leonardo");
        u2.setLastname("Gomes");
        u2.setPassword("s3nh@");

        User u3 = new User();
        u3.setUsername("Bruno");
        u3.setEmail("bruno@bruno.com");
        u3.setCep("57010368");
        u3.setFirstname("Bruno");
        u3.setLastname("Dias");
        u3.setPassword("s3nh@");

        viaCepService.fetchAddressFromViaCep(u1);
        viaCepService.fetchAddressFromViaCep(u2);
        viaCepService.fetchAddressFromViaCep(u3);
        Address a1 = u1.getAddress();
        a1.setUser(u1);
        u1.setAddress(a1);

        Address a2 = u2.getAddress();
        a2.setUser(u2);
        u2.setAddress(a2);

        Address a3 = u3.getAddress();
        a3.setUser(u3);
        u3.setAddress(a3);

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

    }
}
