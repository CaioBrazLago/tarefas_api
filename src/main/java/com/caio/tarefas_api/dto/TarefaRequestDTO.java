package com.caio.tarefas_api.dto;

import com.caio.tarefas_api.model.StatusTarefa;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TarefaRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;

    private StatusTarefa status;
}