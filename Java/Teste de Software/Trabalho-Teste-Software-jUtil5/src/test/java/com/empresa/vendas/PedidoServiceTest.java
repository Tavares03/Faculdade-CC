package com.empresa.vendas;

import org.junit.jupiter.api.RepeatedTest;

class PedidoServiceTest {

    private static int contador = 1;

    @RepeatedTest(3)
    void testeAleatorioDeProcessamentoDePedido() {
        String nomeCliente = "Cliente " + contador++;
        Cliente cliente = new Cliente(nomeCliente);
        Pedido pedido = new Pedido(cliente);
        PedidoService service = new PedidoService();

        System.out.println("\nProcessando pedido para: " + nomeCliente);
        String resultado = service.processarPedido(pedido);

        if (pedido.isFinalizado()) {
            System.out.println(resultado);
        } else if (pedido.isCancelado()) {
            System.out.println(resultado);
        } else {
            System.out.println("Pedido ainda em processamento.");
        }
    }
}