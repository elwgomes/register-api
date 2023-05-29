package com.elwgomes.cadastro.api.repositories;

import com.elwgomes.cadastro.api.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
