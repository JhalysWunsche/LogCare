package com.logcare.api.dto;

import java.time.LocalDate;

public record GrupoFamiliarResponseDTO(
        Long grupoFamiliarId,
        String grupofamiliarNome,
        Long responsavelId,
        String responsavelNome,
        LocalDate grupoFamiliarDataCriacao
) {
}
