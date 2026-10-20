package com.logcare.api.dto;

public record PlanoResponseDTO(
        Long planoId,
        String planoNome,
        Double valorPlano
) {
}
