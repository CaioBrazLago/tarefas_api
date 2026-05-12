package com.caio.tarefas_api.repository;

import com.caio.tarefas_api.model.Tarefa;
import com.caio.tarefas_api.model.StatusTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByStatus(StatusTarefa status);
}