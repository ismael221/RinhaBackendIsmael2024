package com.rinha.ismael.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExtratoDTO {
    private SaldoResponseDTO saldo;
    private List<TransacaoListDTO> ultimas_transacoes;
}
