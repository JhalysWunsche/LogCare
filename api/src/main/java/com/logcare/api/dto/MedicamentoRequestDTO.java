package com.logcare.api.dto;

import java.time.LocalDate;

public record MedicamentoRequestDTO(
        String nomeMedicamento,
        String dosagemMedicamento,
        String quantidadeAtualMedicamento,
        String quantidadeMinimaMedicamento,
        LocalDate dataInicioMedicamento,
        LocalDate dataTerminoMedicamento
) {
}
