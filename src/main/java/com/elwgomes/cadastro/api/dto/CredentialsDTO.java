package com.elwgomes.cadastro.api.dto;


import com.elwgomes.cadastro.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO {

    private String username;
    private String password;

    public CredentialsDTO (User entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
