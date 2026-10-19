package com.logcare.api.service;

import com.logcare.api.dto.UsuarioRequestDTO;
import com.logcare.api.dto.UsuarioResponseDTO;
import com.logcare.api.entity.CidadeEntity;
import com.logcare.api.entity.EstadoEntity;
import com.logcare.api.entity.PlanoEntity;
import com.logcare.api.entity.UsuarioEntity;
import com.logcare.api.repository.CidadeRepository;
import com.logcare.api.repository.EstadoRepository;
import com.logcare.api.repository.PlanoRepository;
import com.logcare.api.repository.UsuarioRepository;
import com.logcare.api.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private final EstadoRepository estadoRepository;
    private final PlanoRepository planoRepository;
    private final CidadeRepository cidadeRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, EstadoRepository estadoRepository, CidadeRepository cidadeRepository, PlanoRepository planoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.planoRepository = planoRepository;
    }

    public List<UsuarioResponseDTO> findAll() {
        List<UsuarioEntity> entidades = usuarioRepository.findAll();
        return entidades.stream()
                .map(entidade -> new UsuarioResponseDTO(
                        entidade.getId(),
                        entidade.getNomeUsuario(),
                        entidade.getEmailUsuario(),
                        entidade.getDataNascimento(),
                        entidade.getTelefoneUsuario(),
                        entidade.getBiografiaUsuario(),
                        entidade.getTipoUsuario(),
                        entidade.getEstadoUsuario().getNomeEstado()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO findById(Long id) {
        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado!"));
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getNomeUsuario(),
                entity.getEmailUsuario(),
                entity.getDataNascimento(),
                entity.getTelefoneUsuario(),
                entity.getBiografiaUsuario(),
                entity.getTipoUsuario(),
                entity.getEstadoUsuario().getNomeEstado()
        );
    }

    @Transactional
    public UsuarioResponseDTO insert(UsuarioRequestDTO dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNomeUsuario(dto.nome());
        entity.setEmailUsuario(dto.email());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setTelefoneUsuario(dto.telefone());
        entity.setBiografiaUsuario(dto.bio());
        entity.setTipoUsuario(dto.tipoUsuario());
        entity.setSenhaUsuario(dto.senha());
        EstadoEntity estado = estadoRepository.findById(dto.estadoId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado!"));
        entity.setEstadoUsuario(estado);
        PlanoEntity plano = planoRepository.findById(dto.planoId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado!"));
        entity.setPlanoUsuario(plano);
        CidadeEntity cidade = cidadeRepository.findById(dto.cidadeId())
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada!"));
        entity.setCidadeUsuario(cidade);
        entity = usuarioRepository.save(entity);

        return new UsuarioResponseDTO(entity.getId(),
                entity.getNomeUsuario(),
                entity.getEmailUsuario(),
                entity.getDataNascimento(),
                entity.getTelefoneUsuario(),
                entity.getBiografiaUsuario(),
                entity.getTipoUsuario(),
                entity.getEstadoUsuario().getNomeEstado());
    }

    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        UsuarioEntity entity = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado!"));
        entity.setNomeUsuario(dto.nome());
        entity.setEmailUsuario(dto.email());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setTelefoneUsuario(dto.telefone());
        entity.setBiografiaUsuario(dto.bio());
        entity.setTipoUsuario(dto.tipoUsuario());
        entity.setSenhaUsuario(dto.senha());
        EstadoEntity estado = estadoRepository.findById(dto.estadoId())
                .orElseThrow(() -> new RuntimeException("Estado não encontrado!"));
        entity.setEstadoUsuario(estado);
        PlanoEntity plano = planoRepository.findById(dto.planoId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado!"));
        entity.setPlanoUsuario(plano);
        CidadeEntity cidade = cidadeRepository.findById(dto.cidadeId())
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada!"));
        entity.setCidadeUsuario(cidade);
        entity = usuarioRepository.save(entity);
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getNomeUsuario(),
                entity.getEmailUsuario(),
                entity.getDataNascimento(),
                entity.getTelefoneUsuario(),
                entity.getBiografiaUsuario(),
                entity.getTipoUsuario(),
                entity.getEstadoUsuario().getNomeEstado());
    }

    @Transactional
    public void delete(Long id) {

        UsuarioEntity entity = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado!"));
        usuarioRepository.delete(entity);

    }
}
