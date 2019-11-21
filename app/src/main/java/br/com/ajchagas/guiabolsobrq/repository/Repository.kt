package br.com.ajchagas.guiabolsobrq.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.ajchagas.guiabolsobrq.model.Banco
import br.com.ajchagas.guiabolsobrq.retrofit.webclient.BancosWebClient

class Repository(
    private val webClient : BancosWebClient = BancosWebClient()
){
    private val listaBancosDaApi = MutableLiveData<Resource<Banco>?>()

    fun buscaListaBancos() : LiveData<Resource<Banco>?>{

        webClient.listaBancos(
            quandoSucesso = {listaDeBancos ->
                listaBancosDaApi.value = Resource(dado = listaDeBancos)
            },
            quandoFalha = { msgErro ->
                val resourceAtual = listaBancosDaApi.value
                listaBancosDaApi.value = criaResourceDeFalha(resourceAtual, msgErro)

            }
        )
        return listaBancosDaApi
    }

}