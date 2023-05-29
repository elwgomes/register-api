package com.elwgomes.cadastro.api;

import com.elwgomes.cadastro.api.entities.Address;
import com.elwgomes.cadastro.api.entities.User;
import com.elwgomes.cadastro.api.repositories.UserRepository;
import com.elwgomes.cadastro.api.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Configuration
	class Fodase implements CommandLineRunner {

		@Autowired
		private UserRepository userRepository;

		@Autowired
		private ViaCepService viaCepService;

		@Override
		public void run(String... args) throws Exception {

			User u1 = new User();
			u1.setUsername("ely");
			u1.setEmail("ely@gmail.com");
			u1.setCep("57075420");
			u1.setFirstname("Elyssana");
			u1.setLastname("Silva");
			u1.setPassword("senha123");

			viaCepService.fetchAddressFromViaCep(u1);
			Address address = (Address) u1.getAddress();
			address.setUser(u1); // Associa o User ao Address

			u1.setAddress(address);

			userRepository.save(u1);

		}
	}

}
