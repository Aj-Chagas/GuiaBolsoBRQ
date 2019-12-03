package br.com.ajchagas.guiabolsobrq.retrofit.webclient

import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.model.listaBancoApi.Banco
import br.com.ajchagas.guiabolsobrq.model.listaExtratoApi.Extrato
import br.com.ajchagas.guiabolsobrq.retrofit.ConfigRetrofit
import br.com.ajchagas.guiabolsobrq.retrofit.service.ServiceBancos

class BancosWebClient(
    private val service : ServiceBancos = ConfigRetrofit().serviceBancos
) {

    fun listaBancos() : Banco?{
        return service.getListaBancos().execute().body()
    }

    fun listaExtrato(
        conta: Conta,
        dataAtual: String,
        ultimos30Dias: String
    ) : Extrato?{
        return service.getExtrato(conta.idBanco, dataAtual, ultimos30Dias).execute().body()
    }

}