package com.elwgomes.cadastro.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private UUID id;

    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    @JsonIgnore
    private User user;

}
