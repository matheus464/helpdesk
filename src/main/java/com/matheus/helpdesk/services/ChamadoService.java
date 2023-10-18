package com.matheus.helpdesk.services;

import com.matheus.helpdesk.Chamado;
import com.matheus.helpdesk.Cliente;
import com.matheus.helpdesk.DTO.ChamadoDTO;
import com.matheus.helpdesk.Tecnico;
import com.matheus.helpdesk.domain.enums.Prioridade;
import com.matheus.helpdesk.domain.enums.Status;
import com.matheus.helpdesk.exceptions.ObjectNotFound;
import com.matheus.helpdesk.repositories.ChamadoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFound("Objeto não encontrado! ID: "+ id));
    }

    public List<Chamado> findAll() {
        List<Chamado> chamadoList = chamadoRepository.findAll();
        return chamadoList;
    }

    public Chamado create(@Valid ChamadoDTO chamadoDTO) {
            return chamadoRepository.save(newChamado(chamadoDTO));
    }

    private Chamado newChamado(ChamadoDTO objDTO){
        Cliente cliente = clienteService.findById(objDTO.getCliente());
        Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());

        Chamado chamado = new Chamado();
        if(objDTO.getId() != null){
            chamado.setId(objDTO.getId());
        }
        chamado.setCliente(cliente);
        chamado.setTecnico(tecnico);
        chamado.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(objDTO.getStatus()));
        chamado.setTitulo(objDTO.getTitulo());
        chamado.setObservacoes(objDTO.getObservacoes());
        return chamado;
    }
}
