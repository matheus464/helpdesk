package com.matheus.helpdesk.repositories;

import com.matheus.helpdesk.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}