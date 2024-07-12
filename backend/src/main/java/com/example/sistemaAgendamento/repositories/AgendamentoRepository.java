package com.example.sistemaAgendamento.repositories;

import com.example.sistemaAgendamento.dtos.AgendamentoRecord;
import com.example.sistemaAgendamento.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<AgendamentoRecord> findAllByOrderByDataAgendamentoAsc();

    @Query("SELECT case when count(a) = 0 then 0 else sum(a.valor + (a.valor * a.taxa * 0.01)) end " +
            "FROM Agendamento a")
    Double sumAllValor();
}