package com.elwgomes.cadastro.api.dto;

import com.elwgomes.cadastro.api.entities.Address;
import com.elwgomes.cadastro.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String cep;
    private Address address;

    public UserDTO (User entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
