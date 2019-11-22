package br.com.ajchagas.guiabolsobrq.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.CadastroContaActivityViewModel

class CadastroContaActivityViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CadastroContaActivityViewModel(repository) as T
    }
}