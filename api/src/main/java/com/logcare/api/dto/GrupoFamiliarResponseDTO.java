package com.logcare.api.dto;

import java.time.LocalDate;

public record GrupoFamiliarResponseDTO(
        Long grupoFamiliarId,
        String nomeGrupoFamiliar,
        Long responsavelId,
        String nomeResponsavel,
        LocalDate dataCriacaoGrupoFamiliar
) {
}
