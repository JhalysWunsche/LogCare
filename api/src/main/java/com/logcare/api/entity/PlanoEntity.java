package com.logcare.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "plano")
@NoArgsConstructor
@AllArgsConstructor
public class PlanoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nomePlano;
    @Column(name = "valor")
    private Double valorPlano;
}