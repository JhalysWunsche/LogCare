package com.logcare.api.service;

import com.logcare.api.dto.PlanoRequestDTO;
import com.logcare.api.dto.PlanoResponseDTO;
import com.logcare.api.entity.PlanoEntity;
import com.logcare.api.entity.UsuarioEntity;
import com.logcare.api.repository.PlanoRepository;
import com.logcare.api.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanoService {
    private PlanoRepository planoRepository;
    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<PlanoResponseDTO> findAll(){
        List<PlanoEntity> entity = planoRepository.findAll();
        return entity.stream().map(entities -> new PlanoResponseDTO(
                entities.getId(),
                entities.getNomePlano(),
                entities.getValorPlano()
        )).toList();
    }
    @Transactional
    public PlanoResponseDTO findById(Long id){
        PlanoEntity entity = planoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado!"));
        return new PlanoResponseDTO(
                entity.getId(),
                entity.getNomePlano(),
                entity.getValorPlano()
        );
    }
    @Transactional
    public PlanoResponseDTO insert(PlanoRequestDTO requestDTO){
        PlanoEntity entity = new PlanoEntity();
        entity.setNomePlano(requestDTO.planoNome());
        entity.setValorPlano(requestDTO.valorPlano());
        entity = planoRepository.save(entity);
        return new PlanoResponseDTO(
                entity.getId(),
                entity.getNomePlano(),
                entity.getValorPlano()
        );
    }
    @Transactional
    public PlanoResponseDTO update(Long id, PlanoRequestDTO requestDTO){
        PlanoEntity entity = planoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado!"));
        entity.setNomePlano(requestDTO.planoNome());
        entity.setValorPlano(requestDTO.valorPlano());
        entity = planoRepository.save(entity);
        return new PlanoResponseDTO(
                entity.getId(),
                entity.getNomePlano(),
                entity.getValorPlano()
        );
    }
    @Transactional
    public void delete(Long id) {

        PlanoEntity entity = planoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado!"));
        planoRepository.delete(entity);

    }

}
