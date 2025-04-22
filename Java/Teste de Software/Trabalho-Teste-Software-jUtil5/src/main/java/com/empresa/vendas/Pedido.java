package com.empresa.vendas;

public class Pedido {
    private Cliente cliente;
    private boolean finalizado;
    private boolean cancelado;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() { return cliente; }
    public boolean isFinalizado() { return finalizado; }
    public boolean isCancelado() { return cancelado; }

    public void finalizar() { this.finalizado = true; }
    public void cancelar() { this.cancelado = true; }
}
