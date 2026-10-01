package com.logcare.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grupo_familiar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoFamiliarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome_familia;
    private String usuario_responsavel;
    private String usuario_familiar;
    private String usuario_cuidador;
    private String usuario_dependente;
}
