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
}