package com.rinha.ismael.model.util;

import lombok.Data;

import java.time.Instant;


public record Saldo(int total,Instant data_extrato,int limite) {
}
