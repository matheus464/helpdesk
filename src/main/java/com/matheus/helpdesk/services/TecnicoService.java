package com.matheus.helpdesk.services;

import com.matheus.helpdesk.DTO.TecnicoDTO;
import com.matheus.helpdesk.Pessoa;
import com.matheus.helpdesk.Tecnico;
import com.matheus.helpdesk.exceptions.ObjectNotFound;
import com.matheus.helpdesk.repositories.PessoaRepository;
import com.matheus.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFound("Objeto não encontrado, id: "+ id));
    }

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objNew){
        objNew.setId(null);
        validaPorCpfEEmail(objNew);
        Tecnico tecnico = new Tecnico(objNew);
        return repository.save(tecnico);
    }

    public void validaPorCpfEEmail(TecnicoDTO objDTO){
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && objDTO.getId() != obj.get().getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());

        if(obj.isPresent() && objDTO.getId() != obj.get().getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
