package com.matheus.helpdesk.resources;

import com.matheus.helpdesk.Cliente;
import com.matheus.helpdesk.DTO.ClienteDTO;
import com.matheus.helpdesk.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objNew){
        Cliente cliente = service.create(objNew);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@Valid @PathVariable Integer id, @RequestBody ClienteDTO clienteDTO){
        Cliente obj = service.update(id, clienteDTO);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
