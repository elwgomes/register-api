package com.elwgomes.cadastro.api.services;

import com.elwgomes.cadastro.api.dto.AddressDTO;
import com.elwgomes.cadastro.api.entities.Address;
import com.elwgomes.cadastro.api.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@JsonInclude
public class ViaCepService {

    public void fetchAddressFromViaCep(User user) throws Exception {
        String url = "https://viacep.com.br/ws/" + user.getCep() + "/json/";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                String responseBody = response.getBody();
                ObjectMapper mapper = new ObjectMapper();
                AddressDTO dto  = mapper.readValue(responseBody, AddressDTO.class);

                Address address = new Address();
                address.setLogradouro(dto.getLogradouro());
                address.setComplemento(dto.getComplemento());
                address.setBairro(dto.getBairro());
                address.setLocalidade(dto.getLocalidade());
                address.setUf(dto.getUf());

                user.setAddress(address);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
