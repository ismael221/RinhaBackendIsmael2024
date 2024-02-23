package com.rinha.ismael.service;

import com.rinha.ismael.model.entities.Transacao;
import com.rinha.ismael.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransacaoService {
    @Autowired
    TransacaoRepository transacaoRepository;

    public Transacao addTransacao(Transacao transacao){
        transacaoRepository.save(transacao);
        return transacao;
    }

    public List<Transacao> listarTransacoes(int id_cliente){
        List<Transacao> transacaos =  transacaoRepository.findByCliente_Id(id_cliente);
        return transacaos;
    }

}
