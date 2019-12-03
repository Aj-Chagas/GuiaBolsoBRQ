package br.com.ajchagas.guiabolsobrq.retrofit.service

import br.com.ajchagas.guiabolsobrq.model.listaBancoApi.Banco
import br.com.ajchagas.guiabolsobrq.model.listaExtratoApi.Extrato
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceBancos {

    @GET("estagio/trabalho/bancos")
    fun getListaBancos() : Call<Banco?>


    @GET("estagio/trabalho/bancos/{id}/extrato/{dataFinal}/{dataAtual}")
    fun getExtrato(
    @Path("id") id: Int?,
    @Path("dataAtual") dataAtual: String,
    @Path("dataFinal") ultimos30Dias: String
) : Call<Extrato?>

}