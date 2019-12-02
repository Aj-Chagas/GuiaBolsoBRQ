package br.com.ajchagas.guiabolsobrq.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.repository.Resource
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.factory.singleArgViewModelFactory

class ListaContaViewModel(
    private val repository: Repository
) : ViewModel() {

    /* // Make a factory
    * val FACTORY = viewModelFactory(::MyViewModel)
    * ```
    *
    * Use the generated factory:
    * ```
    * ViewModelProviders.of(this, FACTORY(argument))
    *
    * ```
    *
    * @param constructor A function (A) -> T that returns an instance of the ViewModel (typically pass
    * the constructor)
    * @return a function of one argument that returns ViewModelProvider.Factory for ViewModelProviders
    */
    companion object {
        val FACTORY = singleArgViewModelFactory(::ListaContaViewModel)
    }

    fun buscaTodos() : LiveData<List<Conta>?> {
        return repository.buscaTodasContasCadastradas()
    }

    fun remove(conta: Conta) {
        return repository.deleta(conta)
    }
}