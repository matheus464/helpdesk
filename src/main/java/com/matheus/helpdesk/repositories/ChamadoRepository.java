package com.matheus.helpdesk.repositories;

import com.matheus.helpdesk.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}