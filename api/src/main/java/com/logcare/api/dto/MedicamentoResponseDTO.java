package com.logcare.api.dto;

import java.time.LocalDate;

public record MedicamentoResponseDTO(
        Long MedicamentoId,
        String nomeMedicamento,
        String dosagemMedicamento,
        LocalDate dataCriacaoMedicamento
) {
}
