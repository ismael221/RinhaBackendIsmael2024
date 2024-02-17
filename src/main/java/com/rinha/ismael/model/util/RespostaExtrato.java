package com.rinha.ismael.model.util;

import com.rinha.ismael.model.entities.Transacao;
import lombok.Data;
import java.util.List;

@Data
public class RespostaExtrato {
    private Saldo saldo;
    private List<Transacao> ultimas_transacaos;
}
