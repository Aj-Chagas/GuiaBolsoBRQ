package br.com.ajchagas.guiabolsobrq.retrofit.service

import br.com.ajchagas.guiabolsobrq.model.Banco
import retrofit2.Call
import retrofit2.http.GET

interface ServiceBancos {

    @GET("estagio/trabalho/bancos")
    fun getListaBancos() : Call<Banco?>

}