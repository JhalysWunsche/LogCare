package com.logcare.api.dto;

import java.time.LocalDate;

public record UsuarioDTO(Long id,
                         String nome,
                         String email,
                         LocalDate dataNascimento,
                         String telefone,
                         String biografia,
                         String tipoUsuario,
                         String nomeEstado) {


}
