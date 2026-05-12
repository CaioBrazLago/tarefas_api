package com.caio.tarefas_api.dto;

import com.caio.tarefas_api.model.StatusTarefa;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TarefaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    private LocalDateTime dataCriacao;
}