package br.com.ajchagas.guiabolsobrq.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.ajchagas.guiabolsobrq.model.Banco
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.repository.Resource

class CadastroContaActivityViewModel (
    private val repository : Repository
) : ViewModel(){

    fun buscaListaBancos() : LiveData<Resource<Banco>?> {
        return repository.buscaListaBancos()
    }
}