package com.logcare.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "grupo_familiar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoFamiliarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome")
    private String nomeGrupoFamiliar;
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private UsuarioEntity usuarioResponsavel;
    @Column(name = "datcri")
    private LocalDate dataCriacaoGrupoFamiliar;
}
