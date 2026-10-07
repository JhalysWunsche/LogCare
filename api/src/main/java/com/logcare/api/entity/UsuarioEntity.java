package com.logcare.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nomusu")
    private String nomeUsuario;
    @Column(name = "datnasc")
    private LocalDate dataNascimento;
    @Column(name = "email")
    private String emailUsuario;
    @Column(name = "tell")
    private String telefoneUsuario;
    @Column(name = "bio")
    private String biografiaUsuario;
    @Column(name = "tipusu")
    private String tipoUsuario;
    @Column(name = "senha")
    private String senhaUsuario;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoEntity idEstadoUsuario;
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private CidadeEntity idCidadeUsuario;
    @ManyToOne
    @JoinColumn(name = "plano_id")
    private PlanoEntity idPlanoUsuario;
    @Column(name = "datcri")
    private LocalDate dataCriacaoUsuario;

}
