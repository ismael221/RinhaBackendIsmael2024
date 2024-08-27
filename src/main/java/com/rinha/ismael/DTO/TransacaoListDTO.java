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
public class TransacaoListDTO {
    private int valor;
    private char tipo;
    private String descricao;
    private Instant realizada_em;
}
