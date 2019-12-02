package br.com.ajchagas.guiabolsobrq.retrofit.webclient

import br.com.ajchagas.guiabolsobrq.model.listaBancoApi.Banco
import br.com.ajchagas.guiabolsobrq.model.listaExtratoApi.Extrato
import br.com.ajchagas.guiabolsobrq.retrofit.ConfigRetrofit
import br.com.ajchagas.guiabolsobrq.retrofit.service.ServiceBancos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BancosWebClient(
    private val service : ServiceBancos = ConfigRetrofit().serviceBancos
) {

    fun listaBancos() : Banco?{
        return service.getListaBancos().execute().body()

    }

    fun listaExtrato() : Extrato?{
        return service.getExtrato().execute().body()
    }

}