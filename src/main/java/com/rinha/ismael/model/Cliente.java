package com.rinha.ismael.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int limite;
    private int saldo;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    List<Transacao> transacaos;
}
