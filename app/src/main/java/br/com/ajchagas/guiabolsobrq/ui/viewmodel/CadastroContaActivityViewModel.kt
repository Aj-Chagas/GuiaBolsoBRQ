package br.com.ajchagas.guiabolsobrq.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.ajchagas.guiabolsobrq.model.listaBancoApi.Banco
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.model.listaExtratoApi.Extrato
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.repository.Resource

class CadastroContaActivityViewModel (
    private val repository : Repository
) : ViewModel(){

    fun buscaListaBancosNaApi() : LiveData<Resource<Banco?>?> {
        return repository.buscaListaBancos()
    }

    fun salva(conta: Conta): LiveData<Resource<Void?>> {
        return repository.salva(conta)
    }

    fun buscaExtrato(
        conta: Conta,
        dataAtual: String,
        ultimos30Dias: String
    ) : LiveData<Resource<Extrato?>?>{
        return repository.buscaExtratoNaApi(conta, dataAtual, ultimos30Dias)
    }
}