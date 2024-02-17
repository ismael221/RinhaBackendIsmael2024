package com.rinha.ismael.model;

import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class Saldo {
    private int total;
    private Instant data_extrato;
    private int limite;
}
