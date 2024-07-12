package com.example.sistemaAgendamento.controllers;

import com.example.sistemaAgendamento.dtos.AgendamentoRecord;
import com.example.sistemaAgendamento.services.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoRecord> post(@RequestBody AgendamentoRecord agendamento) throws Exception {
        AgendamentoRecord saved = agendamentoService.salvar(agendamento);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoRecord>> getAll() {
        return ResponseEntity.ok().body(agendamentoService.getAllAgendamementos());
    }

    @GetMapping("/extrato-total")
    public ResponseEntity<Double> extratoTotalAgendamento() {
        return ResponseEntity.ok().body(agendamentoService.extratoTotalAgendamentos());
    }
}
