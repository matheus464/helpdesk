package com.matheus.helpdesk.repositories;

import com.matheus.helpdesk.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
