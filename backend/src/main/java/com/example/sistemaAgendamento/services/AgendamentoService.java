package com.example.sistemaAgendamento.services;

import com.example.sistemaAgendamento.dtos.AgendamentoRecord;
import com.example.sistemaAgendamento.entities.Agendamento;
import com.example.sistemaAgendamento.repositories.AgendamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    @Transactional
    public AgendamentoRecord salvar(AgendamentoRecord agendamento) throws Exception {
        Agendamento toSave = agendamento.toAgendamento();

        aplicarDataTransferencia(toSave);
        validarContas(toSave);
        aplicarTaxa(toSave);
        return agendamentoRepository.save(toSave).toRecord();
    }

    private void aplicarDataTransferencia(Agendamento toSave) {
        toSave.setDataTransferencia(LocalDate.now());
    }

    private void aplicarTaxa(Agendamento agendamento) throws Exception {
        long diferenca = agendamento.getDataTransferencia().until(agendamento.getDataAgendamento(), ChronoUnit.DAYS);
        double taxa;

        if (diferenca == 0) {
            taxa = 2.5D;
        } else if (diferenca <= 10) {
            taxa = 0D;
        } else if (diferenca <= 20) {
            taxa = 8.2D;
        } else if (diferenca <= 30) {
            taxa = 6.9D;
        } else if (diferenca <= 40) {
            taxa = 4.7D;
        } else if (diferenca <= 50) {
            taxa = 1.7D;
        } else {
            throw new Exception("Nenhuma taxa aplicável, reveja as datas de agendamento");
        }

        agendamento.setTaxa(taxa);
    }

    private void validarContas(Agendamento agendamento) throws Exception {
        String contaDestino = agendamento.getContaDestino();
        String contaOrigem = agendamento.getContaOrigem();

        if (contaDestino == null || contaOrigem == null) {
            throw new Exception("As contas deve ser preenchidas");
        }

        if (!contaDestino.matches("\\d{10}") || !contaOrigem.matches("\\d{10}")) {
            throw new Exception("As contas devem ter 10 digitos e apenas números");
        }

        if (Objects.equals(contaDestino, contaOrigem)) {{
            throw new Exception("As contas não podem ser iguais");
        }}
    }

    @Transactional
    public List<AgendamentoRecord> getAllAgendamementos() {
        return agendamentoRepository.findAllByOrderByDataAgendamentoAsc();
    }

    @Transactional
    public Double extratoTotalAgendamentos() {
        return agendamentoRepository.sumAllValor();
    }

}
