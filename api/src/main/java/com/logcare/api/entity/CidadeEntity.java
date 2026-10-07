package com.logcare.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cidade")
@NoArgsConstructor
@AllArgsConstructor
public class CidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nomeCidade;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoEntity idEstado;
}