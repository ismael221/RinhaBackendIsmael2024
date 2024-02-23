package com.rinha.ismael.repository;

import com.rinha.ismael.model.entities.Cliente;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
