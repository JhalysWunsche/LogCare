package com.logcare.api.resource;

import com.logcare.api.dto.GrupoFamiliarRequestDTO;
import com.logcare.api.dto.GrupoFamiliarResponseDTO;
import com.logcare.api.dto.UsuarioRequestDTO;
import com.logcare.api.dto.UsuarioResponseDTO;
import com.logcare.api.service.GrupoFamiliarService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/grupoFamiliar")
public class GrupoFamiliarResource {
    @Autowired
    private GrupoFamiliarService grupoFamiliarService;

    @GetMapping
    public ResponseEntity <List<GrupoFamiliarResponseDTO>> findAll(){
        return ResponseEntity.ok(grupoFamiliarService.findAll());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<GrupoFamiliarResponseDTO> findById(@PathVariable Long id){
        GrupoFamiliarResponseDTO responseDTO = grupoFamiliarService.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<GrupoFamiliarResponseDTO> insert(@RequestBody GrupoFamiliarRequestDTO requestDTO){
        GrupoFamiliarResponseDTO responseDTO = grupoFamiliarService.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.grupoFamiliarId()).toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }
    @PutMapping(value = "{id}")
    @Transactional
    public ResponseEntity<GrupoFamiliarResponseDTO> update(@PathVariable Long id, @RequestBody GrupoFamiliarRequestDTO requestDTO){
        GrupoFamiliarResponseDTO responseDTO = grupoFamiliarService.update(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    @DeleteMapping(value = "{id}")
    @Transactional
    public ResponseEntity<GrupoFamiliarResponseDTO> delete(@PathVariable Long id){
        grupoFamiliarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
