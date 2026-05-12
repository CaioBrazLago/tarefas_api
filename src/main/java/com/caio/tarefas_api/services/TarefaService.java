package com.caio.tarefas_api.service;

import com.caio.tarefas_api.dto.TarefaRequestDTO;
import com.caio.tarefas_api.dto.TarefaResponseDTO;
import com.caio.tarefas_api.model.StatusTarefa;
import com.caio.tarefas_api.model.Tarefa;
import com.caio.tarefas_api.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public TarefaResponseDTO criar(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setStatus(StatusTarefa.PENDENTE);
        return toResponse(tarefaRepository.save(tarefa));
    }

    public List<TarefaResponseDTO> listarTodas() {
        return tarefaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TarefaResponseDTO buscarPorId(Long id) {
        return toResponse(findById(id));
    }

    public List<TarefaResponseDTO> listarPorStatus(StatusTarefa status) {
        return tarefaRepository.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TarefaResponseDTO atualizar(Long id, TarefaRequestDTO dto) {
        Tarefa tarefa = findById(id);
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setStatus(dto.getStatus());
        return toResponse(tarefaRepository.save(tarefa));
    }

    public void deletar(Long id) {
        findById(id);
        tarefaRepository.deleteById(id);
    }

    private Tarefa findById(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    private TarefaResponseDTO toResponse(Tarefa tarefa) {
        TarefaResponseDTO dto = new TarefaResponseDTO();
        dto.setId(tarefa.getId());
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setStatus(tarefa.getStatus());
        dto.setDataCriacao(tarefa.getDataCriacao());
        return dto;
    }
}