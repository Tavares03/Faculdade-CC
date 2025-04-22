package com.empresa.vendas;

import java.util.Random;

public class AvaliadorCredito {
    public boolean aprovarCredito(Cliente cliente) {
        return new Random().nextBoolean();
    }
}
