package com.empresa.vendas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceTest {

    @Test
    void FinalizarPedidoComCreditoEAprovacaoFinanceira() {
        Cliente cliente = new Cliente("Cliente 1");
        Pedido pedido = new Pedido(cliente);
        PedidoService service = new PedidoService() {
            @Override
            public void processarPedido(Pedido pedido) {
                NotaFiscal nf = new NotaFiscal(pedido);
                nf.enviarPrimeiraVia();
                nf.enviarSegundaVia();
                new DepartamentoExpedicao().separarMercadoria(pedido);
                new DepartamentoFinanceiro().gerarCobranca(pedido);
                pedido.finalizar();
            }
        };

        service.processarPedido(pedido);
        assertTrue(pedido.isFinalizado());
        assertFalse(pedido.isCancelado());
    }

    @Test
    void CancelarPedidoSemCredito() {
        Cliente cliente = new Cliente("Cliente 2");
        Pedido pedido = new Pedido(cliente);
        PedidoService service = new PedidoService() {
            @Override
            public void processarPedido(Pedido pedido) {
                pedido.cancelar();
            }
        };

        service.processarPedido(pedido);
        assertTrue(pedido.isCancelado());
        assertFalse(pedido.isFinalizado());
    }

    @Test
    void CancelarPedidoSeNaoReceberPagamento() {
        Cliente cliente = new Cliente("Cliente 3");
        Pedido pedido = new Pedido(cliente);
        PedidoService service = new PedidoService() {
            @Override
            public void processarPedido(Pedido pedido) {
                NotaFiscal nf = new NotaFiscal(pedido);
                nf.enviarPrimeiraVia();
                nf.enviarSegundaVia();
                new DepartamentoExpedicao().separarMercadoria(pedido);
                new DepartamentoFinanceiro().gerarCobranca(pedido);
                new DepartamentoFinanceiro().enviarCobrancaAdicional();
                pedido.cancelar();
            }
        };

        service.processarPedido(pedido);
        assertTrue(pedido.isCancelado());
        assertFalse(pedido.isFinalizado());
    }
}
