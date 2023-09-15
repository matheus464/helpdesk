package com.matheus.helpdesk.services;

import com.matheus.helpdesk.Tecnico;
import com.matheus.helpdesk.exceptions.ObjectNotFoundException;
import com.matheus.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
            Optional<Tecnico> obj = repository.findById(id);
            return obj.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! id:"+id));
    }
}
