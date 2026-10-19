package com.logcare.api.resource;


import com.logcare.api.dto.UsuarioRequestDTO;
import com.logcare.api.dto.UsuarioResponseDTO;
import com.logcare.api.service.UsuarioService;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity <List<UsuarioResponseDTO>> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <UsuarioResponseDTO> findById(@PathVariable Long id){
        UsuarioResponseDTO responseDTO = usuarioService.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioResponseDTO> insert(@RequestBody UsuarioRequestDTO requestDTO){
        // Recebe Request, devolve Response
        UsuarioResponseDTO responseDTO = usuarioService.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity <UsuarioResponseDTO> update(@PathVariable Long id, @RequestBody UsuarioRequestDTO requestDTO){
        UsuarioResponseDTO responseDTO = usuarioService.update(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
             usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
