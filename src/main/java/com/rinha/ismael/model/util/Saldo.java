package com.rinha.ismael.model.util;

import lombok.Data;

import java.time.Instant;


@Data
public class Saldo {
    private int total;
    private Instant data_extrato;
    private int limite;
}
