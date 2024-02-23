package com.rinha.ismael.model.util;

import com.rinha.ismael.model.entities.Transacao;
import java.util.List;

public record RespostaExtrato(Saldo saldo, List<Transacao> ultimasTransacoes) {
}
