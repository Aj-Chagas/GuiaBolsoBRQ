package br.com.ajchagas.guiabolsobrq.di.modules

import androidx.room.Room
import br.com.ajchagas.guiabolsobrq.database.AppDatabase
import br.com.ajchagas.guiabolsobrq.database.dao.ContaDAO
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.retrofit.webclient.BancoWebClient
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.CadastroContaActivityViewModel
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.ExtratoViewModel
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.ListaContaViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "conta.db"

val appModules = module {
    single {
         Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<AppDatabase>().contaDAO
    }
    single {
        BancoWebClient()
    }
    single {
        Repository(get(), get())
    }
    viewModel {
        CadastroContaActivityViewModel(get())
    }

    viewModel {
        ExtratoViewModel(get())
    }
    viewModel {
        ListaContaViewModel(get())
    }
}