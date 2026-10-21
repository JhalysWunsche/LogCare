package com.logcare.api.resource;

import com.logcare.api.dto.MedicamentoRequestDTO;
import com.logcare.api.dto.MedicamentoResponseDTO;
import com.logcare.api.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/medicamento")
public class MedicamentoResource {
    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping
    public ResponseEntity<List<MedicamentoResponseDTO>> findAll(){
        return ResponseEntity.ok(medicamentoService.findAll());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<MedicamentoResponseDTO> findById(@PathVariable Long id){
        MedicamentoResponseDTO responseDTO = medicamentoService.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<MedicamentoResponseDTO> insert(@RequestBody MedicamentoRequestDTO requestDTO){
        MedicamentoResponseDTO responseDTO = medicamentoService.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.medicamentoId() ).toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }
    @PutMapping(value = "{id}")
    @Transactional
    public ResponseEntity<MedicamentoResponseDTO> update(@PathVariable Long id, @RequestBody MedicamentoRequestDTO requestDTO){
        MedicamentoResponseDTO responseDTO = medicamentoService.update(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    @DeleteMapping(value = "{id}")
    @Transactional
    public ResponseEntity<MedicamentoResponseDTO> delete(@PathVariable Long id){
        medicamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
