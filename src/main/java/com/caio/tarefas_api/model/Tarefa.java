package com.caio.tarefas_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tarefas")
@EqualsAndHashCode(callSuper = true)
public class Tarefa extends BaseEntity {

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTarefa status = StatusTarefa.PENDENTE;
}