package com.logcare.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "medicamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nomeMedicamento;

    @Column(name = "dsg")
    private String dosagemMedicamento;

    @Column(name = "qtdatu")
    private String quantidadeAtualMedicamento;

    @Column(name = "qtdmin")
    private String quantidadeMinimaMedicamento;

    @Column(name = "datini")
    private LocalDate dataInicioMedicamento;

    @Column(name = "datter")
    private LocalDate dataTerminoMedicamento;

    @Column(name = "datcri")
    private LocalDate dataCriacaoMedicamento;

    //Chave Estrangeira ligando ao Grupo Familiar
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private EstadoEntity idEstado;

    // Chave estrangeira ligando ao usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity idUsuario;
}
