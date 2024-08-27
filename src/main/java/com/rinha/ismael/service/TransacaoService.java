package com.rinha.ismael.service;

import com.rinha.ismael.DTO.*;

import com.rinha.ismael.exception.UnprocessableEntityException;
import com.rinha.ismael.model.entities.Cliente;
import com.rinha.ismael.model.entities.Transacao;
import com.rinha.ismael.repository.TransacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransacaoService {
    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ClienteService clienteService;

    public TransacaoListDTO convertToDto(Transacao transacao){
        return modelMapper.map(transacao, TransacaoListDTO.class);
    }

    public ClienteExtratoDTO doNewTransaction(int id_cliente ,Transacao transacao){
            Instant instant = Instant.now();
            Cliente cliente = clienteService.getCliente(id_cliente);

            int limiteDisponivel = cliente.getLimite();
            int saldo = cliente.getSaldo();

            switch (transacao.getTipo()){
                case 'c':
                    saldo += transacao.getValor();
                    break;
                case 'd':
                    if ((saldo - transacao.getValor()) < -limiteDisponivel){
                        throw new UnprocessableEntityException("Saldo do cliente não pode ficar menor que o limite disponivel para o tipo débito");
                    }
                    saldo =  saldo - transacao.getValor();
                    break;
            }

            transacao.setRealizada_em(instant);
            cliente.setSaldo(saldo);
            transacao.setCliente(cliente);
            transacaoRepository.save(transacao);
            Cliente clienteUpdated = clienteService.addSaldoCliente(cliente);

            ClienteExtratoDTO extrato = new ClienteExtratoDTO();
            extrato.setLimite(clienteUpdated.getLimite());
            extrato.setSaldo(cliente.getSaldo());
            return extrato;
    }

    public ExtratoDTO listarTransacoes(int id_cliente){
        List<Transacao> transacaos =  transacaoRepository.findByCliente_Id(id_cliente);
        Cliente clienteFound = clienteService.getCliente(id_cliente);
        SaldoResponseDTO saldo = new SaldoResponseDTO();
        saldo.setTotal(clienteFound.getSaldo());
        saldo.setLimite(clienteFound.getLimite());
        List<TransacaoListDTO> transacoesConverted = transacaos.stream()
                .sorted(Comparator.comparing(Transacao::getRealizada_em).reversed())
                .limit(10)
                .map(this::convertToDto)
                .collect(Collectors.toList());

        ExtratoDTO extratoDTO = new ExtratoDTO();
        extratoDTO.setUltimas_transacoes(transacoesConverted);
        extratoDTO.setSaldo(saldo);

        return extratoDTO;
    }

}
