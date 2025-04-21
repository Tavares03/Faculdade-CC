package com.empresa.vendas;

import java.util.Random;

public class DepartamentoFinanceiro {
    public void gerarCobranca(Pedido pedido) {
        System.out.println("Cobrança bancária gerada.");
    }

    public boolean receberPagamento() {
        return new Random().nextBoolean();
    }

    public void enviarCobrancaAdicional() {
        System.out.println("Cobrança adicional enviada.");
    }
}
