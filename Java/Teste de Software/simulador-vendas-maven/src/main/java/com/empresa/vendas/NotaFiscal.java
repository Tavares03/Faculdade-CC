package com.empresa.vendas;

public class NotaFiscal {
    private Pedido pedido;

    public NotaFiscal(Pedido pedido) { this.pedido = pedido; }

    public void enviarPrimeiraVia() {
        System.out.println("1ª via enviada à expedição.");
    }

    public void enviarSegundaVia() {
        System.out.println("2ª via enviada ao financeiro.");
    }
}
