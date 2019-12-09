package br.com.ajchagas.guiabolsobrq.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.ajchagas.guiabolsobrq.database.dao.ContaDAO
import br.com.ajchagas.guiabolsobrq.model.listaBancoApi.Banco
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.model.listaExtratoApi.Extrato
import br.com.ajchagas.guiabolsobrq.retrofit.webclient.BancoWebClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class Repository(
    private val dao: ContaDAO,
    private val webClient : BancoWebClient
){
    private val listaBancosDaApi = MutableLiveData<Resource<Banco?>?>()

    fun buscaListaBancos(): LiveData<Resource<Banco?>?> {

        return listaBancosDaApi.also { livedata ->
            CoroutineScope(Dispatchers.IO).launch {
               val resource : Resource<Banco?>? = try {
                   Resource(dado = webClient.listaBancos())
               }catch (e : IOException){
                   Resource(dado = null, erro = e.message)
               }
               livedata.postValue(resource)
            }
        }
    }

    fun salva(conta: Conta): LiveData<Resource<Void?>> {
        val livedata = MutableLiveData<Resource<Void?>>()
        CoroutineScope(Dispatchers.IO).launch {
            dao.salva(conta)
            withContext(Dispatchers.Main){
                livedata.value = Resource(dado = null)
            }
        }
        return livedata
    }

    fun buscaTodasContasCadastradas(): LiveData<List<Conta>?> {
        return dao.buscaTodos()
    }

    fun deleta(conta: Conta) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.delete(conta)
        }
    }

    fun buscaExtratoNaApi(
        conta: Conta,
        dataAtual: String,
        ultimos30Dias: String
    ): LiveData<Resource<Extrato?>?> {
        return MutableLiveData<Resource<Extrato?>?>().also { liveData ->
            CoroutineScope(Dispatchers.IO).launch {
                val resource : Resource<Extrato?>? = try {
                    Resource(dado = webClient.listaExtrato(conta, dataAtual, ultimos30Dias))
                }catch (e : IOException){
                    Resource(dado = null, erro = e.message)
                }
                liveData.postValue(resource)
            }
        }
    }

    fun edita(conta: Conta) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.edita(conta)
        }
    }
}