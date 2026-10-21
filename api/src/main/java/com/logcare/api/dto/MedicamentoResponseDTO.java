package com.logcare.api.dto;

import java.time.LocalDate;

public record MedicamentoResponseDTO(
        Long medicamentoId,
        String nomeMedicamento,
        String dosagemMedicamento,
        LocalDate dataCriacaoMedicamento
) {
}
