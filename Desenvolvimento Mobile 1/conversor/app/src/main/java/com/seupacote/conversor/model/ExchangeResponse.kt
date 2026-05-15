package com.seupacote.conversor.model

data class ExchangeResponse(
    val base: String,
    val rates: Map<String, Double>
)
