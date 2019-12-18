package br.com.ajchagas.guiabolsobrq.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.model.listaExtratoApi.Extrato
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.repository.Resource
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.factory.singleArgViewModelFactory

class ExtratoViewModel(
    private val repository: Repository
) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::ExtratoViewModel)
    }

    fun buscaExtrato(
        conta: Conta,
        dataAtual: String,
        ultimos30Dias: String
    ) : LiveData<Resource<Extrato?>?>{
        return repository.buscaExtratoNaApi(conta, dataAtual, ultimos30Dias)
    }

    fun edita(conta: Conta) {
        return repository.edita(conta)
    }

}