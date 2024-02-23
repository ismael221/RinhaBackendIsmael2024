package com.rinha.ismael.repository;

import com.rinha.ismael.model.entities.Transacao;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Repository
public interface TransacaoRepository  extends JpaRepository<Transacao,Integer> {
    List<Transacao> findByCliente_Id(int id);
}
