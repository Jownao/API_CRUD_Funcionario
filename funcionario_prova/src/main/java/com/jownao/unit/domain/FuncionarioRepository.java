package com.jownao.unit.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> { // interface repository -> sptring data JPA

    Optional<Funcionario> findByNome(String nome); //nome completo
    Optional<Funcionario> findByNomeContaining(String nome);//partes do nome
    Optional<Funcionario> findByNomeContainingIgnoreCase(String nome);//ignorar caixa alta

}
