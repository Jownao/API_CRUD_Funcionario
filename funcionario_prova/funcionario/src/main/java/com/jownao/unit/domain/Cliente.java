package com.jownao.unit.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Cliente { //POJO --> Domain Entity
    
    @Id private long matricula;
    private String nome;
    private String email;



    public Cliente() {
    }

    

    public Cliente(long matricula, String nome, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
    }

}

    
