package com.elwgomes.cadastro.api.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String complement;
    private String nbhood;
    private String city;
    private String state;

}
