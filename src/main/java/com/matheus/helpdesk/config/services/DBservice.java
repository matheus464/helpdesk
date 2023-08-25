package com.matheus.helpdesk.config.services;

import com.matheus.helpdesk.Chamado;
import com.matheus.helpdesk.Cliente;
import com.matheus.helpdesk.Tecnico;
import com.matheus.helpdesk.domain.enums.Perfil;
import com.matheus.helpdesk.domain.enums.Prioridade;
import com.matheus.helpdesk.domain.enums.Status;
import com.matheus.helpdesk.repositories.ChamadoRepository;
import com.matheus.helpdesk.repositories.ClienteRepository;
import com.matheus.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBservice {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void instanciaDB(){
        Tecnico t1 = new Tecnico(null, "Valdir Cezar", "valdir@mail.com", "51977459080", "123456");
        t1.addPerfis(Perfil.ADMIN);

        Cliente c1 = new Cliente(null, "Linus Valdosano", "linus@mail.com", "80591627051", "123");

        Chamado chamois2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamois 1", "prioridade media",t1, c1);

        chamadoRepository.saveAll(Arrays.asList(chamois2));
        tecnicoRepository.saveAll(Arrays.asList(t1));
        clienteRepository.saveAll(Arrays.asList(c1));
    }
}
