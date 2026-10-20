package com.logcare.api.resource;


import com.logcare.api.dto.PlanoRequestDTO;
import com.logcare.api.dto.PlanoResponseDTO;
import com.logcare.api.repository.PlanoRepository;
import com.logcare.api.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/plano")
public class PlanoResource {
    @Autowired
    private PlanoService planoService;

    @GetMapping
    public ResponseEntity<List<PlanoResponseDTO>> findAll() {
        return ResponseEntity.ok().body(planoService.findAll());
    }
    @GetMapping(value = "{id}")
    @Transactional
    public ResponseEntity<PlanoResponseDTO> findById(@PathVariable Long id){
        PlanoResponseDTO responseDTO = planoService.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<PlanoResponseDTO> insert(@RequestBody PlanoRequestDTO requestDTO){
        PlanoResponseDTO responseDTO = planoService.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.planoId()).toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }
    @PutMapping(value = "{id}")
    @Transactional
    public ResponseEntity<PlanoResponseDTO> update(@PathVariable Long id, @RequestBody PlanoRequestDTO requestDTO){
        PlanoResponseDTO responseDTO = planoService.update(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    @DeleteMapping(value = "{id}")
    @Transactional
    public ResponseEntity<PlanoResponseDTO> delete(@PathVariable Long id){
        planoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
