package com.rinha.ismael.controller;

import com.rinha.ismael.DTO.ClienteExtratoDTO;
import com.rinha.ismael.DTO.ExtratoDTO;
import com.rinha.ismael.DTO.TransacaoDTO;
import com.rinha.ismael.model.entities.Transacao;
import com.rinha.ismael.service.ClienteService;
import com.rinha.ismael.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class TransacaoController {
    @Autowired
    TransacaoService transacaoService;
    @Autowired
    ClienteService clienteService;

    @PostMapping("{id}/transacoes")
    public ResponseEntity<ClienteExtratoDTO> fazerTransacao(@PathVariable int id, @RequestBody Transacao transacao){
        ClienteExtratoDTO clienteExtratoDTO = transacaoService.doNewTransaction(id,transacao);
        return new ResponseEntity<>(clienteExtratoDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<ExtratoDTO> listarExtrato(@PathVariable int id){
        return  new ResponseEntity<>(transacaoService.listarTransacoes(id),HttpStatus.OK);
    }
}
