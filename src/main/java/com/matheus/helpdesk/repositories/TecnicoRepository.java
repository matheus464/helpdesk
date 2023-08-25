package com.matheus.helpdesk.repositories;

import com.matheus.helpdesk.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}