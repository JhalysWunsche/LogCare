package com.logcare.api.service;

import com.logcare.api.dto.GrupoFamiliarRequestDTO;
import com.logcare.api.dto.GrupoFamiliarResponseDTO;
import com.logcare.api.entity.GrupoFamiliarEntity;
import com.logcare.api.entity.UsuarioEntity;
import com.logcare.api.repository.GrupoFamiliarRepository;
import com.logcare.api.repository.UsuarioRepository;
import com.logcare.api.service.exception.ResourceExceptionHandler;
import com.logcare.api.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoFamiliarService {
    @Autowired
    private GrupoFamiliarRepository grupoFamiliarRepository;
    private UsuarioRepository usuarioRepository;

    public GrupoFamiliarService(GrupoFamiliarRepository grupoFamiliarRepository, UsuarioRepository usuarioRepository) {
        this.grupoFamiliarRepository = grupoFamiliarRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<GrupoFamiliarResponseDTO> findAll() {
        List<GrupoFamiliarEntity> grupos = grupoFamiliarRepository.findAll();
        return grupos.stream().map(grupo -> new GrupoFamiliarResponseDTO(
                grupo.getId(),
                grupo.getNomeGrupoFamiliar(),
                grupo.getUsuarioResponsavel().getId(),
                grupo.getUsuarioResponsavel().getNomeUsuario(),
                grupo.getDataCriacaoGrupoFamiliar()
        )).toList();
    }

    @Transactional(readOnly = true)
    public GrupoFamiliarResponseDTO findById(Long id) {
        GrupoFamiliarEntity entity = grupoFamiliarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo Familiar não encontrado"));
        return new GrupoFamiliarResponseDTO(
                entity.getId(),
                entity.getNomeGrupoFamiliar(),
                entity.getUsuarioResponsavel().getId(),
                entity.getUsuarioResponsavel().getNomeUsuario(),
                entity.getDataCriacaoGrupoFamiliar()
        );
    }

    @Transactional
    public GrupoFamiliarResponseDTO insert(GrupoFamiliarRequestDTO dto) {
        UsuarioEntity responsavel = usuarioRepository.findById(dto.responsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário responsável não encontrado!"));
        GrupoFamiliarEntity entity = new GrupoFamiliarEntity();
        entity.setNomeGrupoFamiliar(dto.nomeGrupoFamiliar());
        entity.setUsuarioResponsavel(responsavel);
        entity = grupoFamiliarRepository.save(entity);
        return new GrupoFamiliarResponseDTO(
                entity.getId(),
                entity.getNomeGrupoFamiliar(),
                entity.getUsuarioResponsavel().getId(),
                entity.getUsuarioResponsavel().getNomeUsuario(),
                entity.getDataCriacaoGrupoFamiliar()
        );
    }

    @Transactional
    public GrupoFamiliarResponseDTO update(Long id, GrupoFamiliarRequestDTO dto) {
        UsuarioEntity responsavel = usuarioRepository.findById(dto.responsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário responsável não encontrado!"));
        GrupoFamiliarEntity entity = grupoFamiliarRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grupo Familiar Não encontrado!"));
        entity.setNomeGrupoFamiliar(dto.nomeGrupoFamiliar());
        entity.setUsuarioResponsavel(responsavel);
        entity = grupoFamiliarRepository.save(entity);
        return new GrupoFamiliarResponseDTO(
                entity.getId(),
                entity.getNomeGrupoFamiliar(),
                entity.getUsuarioResponsavel().getId(),
                entity.getUsuarioResponsavel().getNomeUsuario(),
                entity.getDataCriacaoGrupoFamiliar()
        );
    }
    public void delete(Long id) {
        GrupoFamiliarEntity entity = grupoFamiliarRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grupo Familiar Não encontrado!"));
        grupoFamiliarRepository.deleteById(id);
    }
}

