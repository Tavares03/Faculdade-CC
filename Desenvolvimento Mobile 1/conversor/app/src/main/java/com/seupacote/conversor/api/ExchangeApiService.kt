package com.seupacote.conversor.api

import com.seupacote.conversor.model.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeApiService {
    // API gratuita sem necessidade de chave — https://open.er-api.com
    // Limite: 1500 requisições/mês
    @GET("v4/latest/{base}")
    suspend fun getRates(
        @Path("base") base: String
    ): ExchangeResponse
}
