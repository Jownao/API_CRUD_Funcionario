package com.jownao.unit.domain;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Funcionario {
    
    @Id private long matricula;
    private String nome;
    private String email;
    private LocalDate dataDeCadastro;
    
    public Funcionario() {
    }

    public Funcionario(long matricula, String nome, String email, LocalDate dataDeCadastro) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.setDataDeCadastro(LocalDate.now());
    }

    @Override
    public String toString() {
        return "{" +
            " matricula='" + getMatricula() + "'" +
            ", nome='" + getNome() + "'" +
            ", email='" + getEmail() + "'" +
            ", dataDeCadastro='" + getDataDeCadastro() + "'" +
            "}";
    }


}
