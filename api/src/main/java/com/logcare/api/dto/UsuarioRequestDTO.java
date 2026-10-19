package com.logcare.api.dto;

import java.time.LocalDate;

public record UsuarioRequestDTO(
        String nome,
        String email,
        LocalDate dataNascimento,
        String telefone,
        String bio,
        String tipoUsuario,
        String senha,       // A senha entra aqui para ser criptografada no Service
        Long estadoId,
        Long cidadeId,
        Long planoId) {
}


