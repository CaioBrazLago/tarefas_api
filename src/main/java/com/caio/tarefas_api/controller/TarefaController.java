package com.caio.tarefas_api.controller;

import com.caio.tarefas_api.dto.TarefaRequestDTO;
import com.caio.tarefas_api.dto.TarefaResponseDTO;
import com.caio.tarefas_api.model.StatusTarefa;
import com.caio.tarefas_api.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criar(@RequestBody @Valid TarefaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(tarefaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @GetMapping("/status")
    public ResponseEntity<List<TarefaResponseDTO>> listarPorStatus(@RequestParam StatusTarefa status) {
        return ResponseEntity.ok(tarefaService.listarPorStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TarefaRequestDTO dto) {
        return ResponseEntity.ok(tarefaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}