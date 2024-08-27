package com.rinha.ismael.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaldoResponseDTO {
    private int total;
    private Instant data_extrato = Instant.now();
    private int limite;
}
