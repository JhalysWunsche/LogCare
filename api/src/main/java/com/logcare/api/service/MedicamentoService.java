package com.logcare.api.service;

import com.logcare.api.dto.MedicamentoRequestDTO;
import com.logcare.api.dto.MedicamentoResponseDTO;
import com.logcare.api.entity.MedicamentoEntity;
import com.logcare.api.repository.MedicamentoRepository;
import com.logcare.api.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

        public MedicamentoService(MedicamentoRepository medicamentoRepository){
            this.medicamentoRepository = medicamentoRepository;
        }

        public List<MedicamentoResponseDTO> findAll(){
            List<MedicamentoEntity> medicamentos = medicamentoRepository.findAll();
                return medicamentos.stream().map(medicamento -> new MedicamentoResponseDTO(
                    medicamento.getId(),
                    medicamento.getNomeMedicamento(),
                    medicamento.getDosagemMedicamento(),
                    medicamento.getDataCriacaoMedicamento()
            )).toList();
        }

        @Transactional(readOnly = true)
        public MedicamentoResponseDTO findById(Long id){
        MedicamentoEntity entity =  medicamentoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado!"));
            return new MedicamentoResponseDTO(
                    entity.getId(),
                    entity.getNomeMedicamento(),
                    entity.getDosagemMedicamento(),
                    entity.getDataCriacaoMedicamento()
            );
        }
        @Transactional
        public MedicamentoResponseDTO insert(MedicamentoRequestDTO dto){
            MedicamentoEntity entity = new MedicamentoEntity();
            entity.setNomeMedicamento(dto.nomeMedicamento());
            entity.setDosagemMedicamento(dto.dosagemMedicamento());
            entity = medicamentoRepository.save(entity);
            return new MedicamentoResponseDTO(
                    entity.getId(),
                    entity.getNomeMedicamento(),
                    entity.getDosagemMedicamento(),
                    entity.getDataCriacaoMedicamento()
            );
        }
        @Transactional
        public MedicamentoResponseDTO update(Long id, MedicamentoRequestDTO dto) {
            MedicamentoEntity entity = medicamentoRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado!"));
            entity.setNomeMedicamento(dto.nomeMedicamento());
            entity.setDosagemMedicamento(dto.dosagemMedicamento());
            entity = medicamentoRepository.save(entity);
            return new MedicamentoResponseDTO(
                    entity.getId(),
                    entity.getNomeMedicamento(),
                    entity.getDosagemMedicamento(),
                    entity.getDataCriacaoMedicamento()
            );
        }
        @Transactional
       public void delete(Long id) {
                MedicamentoEntity entity = medicamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado!"));
                medicamentoRepository.delete(entity);
       }



    }
