package com.matheus.helpdesk.resources;

import com.matheus.helpdesk.Chamado;
import com.matheus.helpdesk.DTO.ChamadoDTO;
import com.matheus.helpdesk.services.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> chamadoList = chamadoService.findAll();
        List<ChamadoDTO> chamadoDTOS = chamadoList.stream().map(chamado -> new ChamadoDTO(chamado)).toList();
        return ResponseEntity.ok().body(chamadoDTOS);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@RequestBody ChamadoDTO chamadoDTO) {
        Chamado chamado = chamadoService.create(chamadoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @RequestBody ChamadoDTO obj){
        Chamado chamado = chamadoService.update(id, obj);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}
