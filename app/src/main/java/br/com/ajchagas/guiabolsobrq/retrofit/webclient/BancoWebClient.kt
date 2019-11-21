package br.com.ajchagas.guiabolsobrq.retrofit.webclient

import br.com.ajchagas.guiabolsobrq.model.Banco
import br.com.ajchagas.guiabolsobrq.retrofit.ConfigRetrofit
import br.com.ajchagas.guiabolsobrq.retrofit.service.ServiceBancos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BancosWebClient(
    private val service : ServiceBancos = ConfigRetrofit().serviceBancos
) {

    fun<T> executaRequisicao(
        call: Call<T>,
        quandoSucesso: (bancos : T?) -> Unit,
        quandoFalha: (erro : String) -> Unit
    ){
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                t.message?.let { quandoFalha(it) }
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.isSuccessful){
                    quandoSucesso(response.body())
                }else{
                    quandoFalha("Falha na requisicao")
                }
            }

        })
    }

    fun listaBancos(
        quandoSucesso: (bancos : Banco?) -> Unit,
        quandoFalha: (erro: String) -> Unit
    ){
        executaRequisicao(
            service.getListaBancos(),
            quandoSucesso,
            quandoFalha
        )
    }

}