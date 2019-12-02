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

    fun buscaExtrato(conta: Conta) : LiveData<Resource<Extrato?>?>{
        return repository.buscaExtratoNaApi()
    }

    companion object {
        val FACTORY = singleArgViewModelFactory(::ExtratoViewModel)
    }
}