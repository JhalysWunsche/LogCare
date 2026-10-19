package com.logcare.api.resource;


import com.logcare.api.dto.EstadoRequestDTO;
import com.logcare.api.dto.EstadoResponseDTO;
import com.logcare.api.dto.UsuarioResponseDTO;
import com.logcare.api.entity.EstadoEntity;
import com.logcare.api.repository.EstadoRepository;
import com.logcare.api.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity <List<EstadoResponseDTO>> findAll() {
        return ResponseEntity.ok(estadoService.findAll());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<EstadoResponseDTO> findById(@PathVariable Long id){
        EstadoResponseDTO reponseDTO = estadoService.findById(id);
        return ResponseEntity.ok().body(reponseDTO);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoResponseDTO> insert(@RequestBody EstadoRequestDTO requestDTO){
        EstadoResponseDTO responseDTO = estadoService.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping(value = "{id}")
    @Transactional
    public ResponseEntity<EstadoResponseDTO> update(@PathVariable Long id, @RequestBody EstadoRequestDTO requestDTO){
        EstadoResponseDTO responseDTO = estadoService.update(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @Transactional
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
