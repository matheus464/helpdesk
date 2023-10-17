package com.matheus.helpdesk.services;

import com.matheus.helpdesk.Chamado;
import com.matheus.helpdesk.exceptions.ObjectNotFound;
import com.matheus.helpdesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFound("Objeto não encontrado! ID: "+ id));
    }
}
