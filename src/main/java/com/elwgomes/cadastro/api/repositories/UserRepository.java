package com.elwgomes.cadastro.api.repositories;

import com.elwgomes.cadastro.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
