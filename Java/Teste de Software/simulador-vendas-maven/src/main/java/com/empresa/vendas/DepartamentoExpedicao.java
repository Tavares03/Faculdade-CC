package com.empresa.vendas;

public class DepartamentoExpedicao {
    public void separarMercadoria(Pedido pedido) {
        System.out.println("Mercadoria separada para o cliente: " + pedido.getCliente().getNome());
    }
}
