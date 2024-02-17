package com.rinha.ismael.model;

import lombok.Data;
import java.util.List;

@Data
public class RespostaExtrato {
    private Saldo saldo;
    private List<Transacao> ultimas_transacaos;
}
