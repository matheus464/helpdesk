package com.matheus.helpdesk.services;

import com.matheus.helpdesk.Cliente;
import com.matheus.helpdesk.DTO.ClienteDTO;
import com.matheus.helpdesk.Pessoa;
import com.matheus.helpdesk.exceptions.ObjectNotFound;
import com.matheus.helpdesk.repositories.ClienteRepository;
import com.matheus.helpdesk.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFound("Objeto não encontrado, id: "+ id));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objNew){
        objNew.setId(null);
        validaPorCpfEEmail(objNew);
        Cliente obj = new Cliente(objNew);
        return repository.save(obj);
    }

    public Cliente update(Integer id, ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente oldObj = findById(id);
        validaPorCpfEEmail(clienteDTO);
        oldObj = new Cliente(clienteDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Cliente obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("O tecnico possui ordens de serviço e não pode ser deletado");
        }
        repository.deleteById(id);
    }

    public void validaPorCpfEEmail(ClienteDTO objDTO){
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
