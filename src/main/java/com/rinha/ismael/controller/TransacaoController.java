package com.rinha.ismael.controller;

import com.rinha.ismael.exception.UnprocessableEntityException;
import com.rinha.ismael.model.*;
import com.rinha.ismael.service.ClienteService;
import com.rinha.ismael.service.TransacaoService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/clientes")
public class TransacaoController {
    @Autowired
    TransacaoService transacaoService;
    @Autowired
    ClienteService clienteService;

    @PostMapping("{id}/transacoes")
    public ResponseEntity<RespostaTransacao> fazerTransacao(@PathVariable int id, @RequestBody Transacao transacao){
                Cliente cliente = clienteService.mostraSaldoCliente(id);
                    var limite =  cliente.getLimite();
                    var saldo =  cliente.getSaldo();
                    var valorTransacao = transacao.getValor();
                    var debito = saldo - valorTransacao;
                    var credito = saldo + valorTransacao;
                    var tipo = transacao.getTipo();
                    if(debito >= -limite && tipo =='d'){
                        transacao.setCliente(cliente);
                        Instant instant = Instant.now();
                        transacao.setRealizada_em(instant);
                        transacaoService.addTransacao(transacao);
                        RespostaTransacao respostaTransacao = new RespostaTransacao();
                        respostaTransacao.setLimite(limite);
                        respostaTransacao.setSaldo(debito);
                        Cliente c = new Cliente();
                        c.setId(id);
                        c.setLimite(limite);
                        c.setSaldo(debito);
                        clienteService.addSaldoCliente(c);
                        return new ResponseEntity<>(respostaTransacao,HttpStatus.OK);
                    } else if (tipo =='c') {
                        transacao.setCliente(cliente);
                        Instant instant = Instant.now();
                        transacao.setRealizada_em(instant);
                        transacaoService.addTransacao(transacao);
                        RespostaTransacao respostaTransacao = new RespostaTransacao();
                        respostaTransacao.setLimite(limite);
                        respostaTransacao.setSaldo(credito);
                        Cliente c = new Cliente();
                        c.setId(id);
                        c.setLimite(limite);
                        c.setSaldo(credito);
                        clienteService.addSaldoCliente(c);
                        return new ResponseEntity<>(respostaTransacao,HttpStatus.OK);
                    }else {
                        throw new UnprocessableEntityException("Saldo do cliente não pode ficar menor que o limite disponivel");
                    }
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<RespostaExtrato> listarExtrato(@PathVariable int id){
        RespostaExtrato extrato = new RespostaExtrato();
        Saldo saldo = new Saldo();
        Cliente cliente = clienteService.mostraSaldoCliente(id);
        //Objeto Saldo:
        saldo.setTotal(cliente.getSaldo());
        Instant instant = Instant.now();
        saldo.setData_extrato(instant);
        saldo.setLimite(cliente.getLimite());
        extrato.setSaldo(saldo);
        extrato.setUltimas_transacaos(transacaoService.listarTransacoes(id));
        //Lista de objetos transacoes:
        return new ResponseEntity<>(extrato,HttpStatus.OK);
    }
}