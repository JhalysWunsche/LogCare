package com.logcare.api.service;

import com.logcare.api.dto.EstadoRequestDTO;
import com.logcare.api.dto.EstadoResponseDTO;
import com.logcare.api.dto.UsuarioResponseDTO;
import com.logcare.api.entity.EstadoEntity;
import com.logcare.api.entity.UsuarioEntity;
import com.logcare.api.repository.EstadoRepository;
import com.logcare.api.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }
    public List<EstadoResponseDTO> findAll() {
        return estadoRepository.findAll().stream()
                .map(entidade -> new EstadoResponseDTO(
                        entidade.getId(),
                        entidade.getNomeEstado()
                )).toList();
    }

    @Transactional(readOnly = true)
    public EstadoResponseDTO findById(Long id) {
        EstadoEntity entity = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado!"));
        return new EstadoResponseDTO(
                entity.getId(),
                entity.getNomeEstado()
        );
    }
    @Transactional
    public EstadoResponseDTO insert(EstadoRequestDTO dto) {
        EstadoEntity entity = new EstadoEntity();
        entity.setNomeEstado(dto.nomeEstado());
        entity = estadoRepository.save(entity);
        return new EstadoResponseDTO(
                entity.getId(),
                entity.getNomeEstado()
        );
    }
    @Transactional
    public EstadoResponseDTO update(Long id, EstadoRequestDTO dto) {
        EstadoEntity entity = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado!"));
        entity.setNomeEstado(dto.nomeEstado());
        entity = estadoRepository.save(entity);
        return new  EstadoResponseDTO(
                entity.getId(),
                entity.getNomeEstado()
        );
    }

    @Transactional
    public void delete(Long id) {
        EstadoEntity entity = estadoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado!"));
        estadoRepository.delete(entity);
    }
}
