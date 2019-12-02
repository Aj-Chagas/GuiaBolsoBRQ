package br.com.ajchagas.guiabolsobrq.retrofit.service

import br.com.ajchagas.guiabolsobrq.model.listaBancoApi.Banco
import br.com.ajchagas.guiabolsobrq.model.listaExtratoApi.Extrato
import retrofit2.Call
import retrofit2.http.GET

interface ServiceBancos {

    @GET("estagio/trabalho/bancos")
    fun getListaBancos() : Call<Banco?>

    @GET("estagio/trabalho/bancos/6/extrato/20191001/20191130")
    fun getExtrato() : Call<Extrato?>

}