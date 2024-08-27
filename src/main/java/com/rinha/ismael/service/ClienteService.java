package com.rinha.ismael.service;

import com.rinha.ismael.exception.ResourceNotFoundException;
import com.rinha.ismael.model.entities.Cliente;
import com.rinha.ismael.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public Cliente getCliente(int id_cliente){
       return clienteRepository.findById(id_cliente).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    public Cliente addSaldoCliente(Cliente c){
        return clienteRepository.save(c);
    }
}
