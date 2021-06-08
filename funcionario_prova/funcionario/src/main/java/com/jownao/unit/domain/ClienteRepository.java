package com.jownao.unit.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> { // interface repository -> sptring data JPA

    Optional<Cliente> findByNome(String nome); //nome completo
    Optional<Cliente> findByNomeContaining(String nome);//partes do nome
    Optional<Cliente> findByNomeContainingIgnoreCase(String nome);//ignorar caixa alta

}
