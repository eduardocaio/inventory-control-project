package com.eduardocaio.inventory_control_project.entities.pk;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class AddressPK {

    @JoinColumn(name = "cep")
    private String cep;

    @JoinColumn(name = "street")
    private String logradouro;

    private String complemento;

    @JoinColumn(name = "zone")
    private String bairro;

    @JoinColumn(name = "city")
    private String localidade;

    @JoinColumn(name = "uf")
    private String uf;

}
