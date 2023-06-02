package com.elwgomes.cadastro.api.repositories;

import com.elwgomes.cadastro.api.dto.UserDTO;
import com.elwgomes.cadastro.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
