package com.example.sistemaAgendamento.dtos;

import com.example.sistemaAgendamento.entities.Agendamento;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.sistemaAgendamento.entities.Agendamento}
 */
public record AgendamentoRecord(Long id, String contaOrigem, String contaDestino, Double valor,
                                LocalDate dataAgendamento, LocalDate dataTransferencia,
                                Double taxa) implements Serializable {

    public Agendamento toAgendamento() {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(id);
        agendamento.setContaOrigem(contaOrigem);
        agendamento.setContaDestino(contaDestino);
        agendamento.setValor(valor);
        agendamento.setDataAgendamento(dataAgendamento);
        agendamento.setDataTransferencia(dataTransferencia);
        agendamento.setTaxa(taxa);
        return agendamento;
    }

}