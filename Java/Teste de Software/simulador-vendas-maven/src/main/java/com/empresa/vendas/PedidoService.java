package com.empresa.vendas;

public class PedidoService {

    private AvaliadorCredito avaliador = new AvaliadorCredito();
    private DepartamentoExpedicao expedicao = new DepartamentoExpedicao();
    private DepartamentoFinanceiro financeiro = new DepartamentoFinanceiro();

    public void processarPedido(Pedido pedido) {
        if (avaliador.aprovarCredito(pedido.getCliente())) {
            NotaFiscal nf = new NotaFiscal(pedido);
            nf.enviarPrimeiraVia();
            nf.enviarSegundaVia();

            expedicao.separarMercadoria(pedido);
            financeiro.gerarCobranca(pedido);

            if (financeiro.receberPagamento()) {
                pedido.finalizar();
            } else {
                financeiro.enviarCobrancaAdicional();
                pedido.cancelar();
            }
        } else {
            pedido.cancelar();
        }
    }
}
