package com.seupacote.conversor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seupacote.conversor.api.RetrofitClient
import kotlinx.coroutines.launch

class ConversorViewModel : ViewModel() {

    private val _taxas = MutableLiveData<Map<String, Double>>()
    val taxas: LiveData<Map<String, Double>> = _taxas

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _erro = MutableLiveData<String?>(null)
    val erro: LiveData<String?> = _erro

    private val _resultado = MutableLiveData<Double?>(null)
    val resultado: LiveData<Double?> = _resultado

    private val _taxaAtual = MutableLiveData<Triple<String, String, Double>?>(null)
    val taxaAtual: LiveData<Triple<String, String, Double>?> = _taxaAtual

    private val _moedaBase = MutableLiveData<String>("BRL")
    val moedaBase: LiveData<String> = _moedaBase

    fun buscarTaxas(moedaBase: String) {
        _moedaBase.value = moedaBase
        _loading.value = true
        _erro.value = null
        _resultado.value = null
        _taxaAtual.value = null

        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getRates(moedaBase)
                _taxas.value = response.rates
                _loading.value = false
            } catch (e: Exception) {
                _erro.value = "Erro ao buscar taxas. Verifique sua conexão."
                _loading.value = false
            }
        }
    }

    fun converter(valor: Double, moedaOrigem: String, moedaDestino: String) {
        val rates = _taxas.value
        if (rates == null) {
            _erro.value = "Taxas ainda sendo carregadas. Aguarde."
            return
        }

        val taxa = rates[moedaDestino]
        if (taxa != null) {
            _resultado.value = valor * taxa
            _taxaAtual.value = Triple(moedaOrigem, moedaDestino, taxa)
            _erro.value = null
        } else {
            _erro.value = "Taxa não encontrada para $moedaDestino."
        }
    }

    fun limparResultado() {
        _resultado.value = null
        _taxaAtual.value = null
    }
}
