package br.com.ajchagas.guiabolsobrq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.model.Transacao
import br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter.ListTransacoesAdapter
import kotlinx.android.synthetic.main.recyclerview_list_transacoes.*

class ExtratoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extrato)

        val listaClientes : MutableList<Transacao> = mutableListOf()

        val mutableListOf = mutableListOf<Transacao>(
            Transacao("Quitanta da Marcia", "18 NOV", "RS 15,99"),
            Transacao("Mercado X Loja 1", "18 NOV", "RS 15,99"),
            Transacao("Sergipe", "18 NOV", "RS 15,99"),
            Transacao("Alemão", "18 NOV", "RS 15,99"),
            Transacao("Quitanta da Marcia", "18 NOV", "RS 15,99"),
            Transacao("Mercado X Loja 1", "18 NOV", "RS 15,99"),
            Transacao("Sergipe", "18 NOV", "RS 15,99"),
            Transacao("Alemão", "18 NOV", "RS 15,99"),
            Transacao("Quitanta da Marcia", "18 NOV", "RS 15,99"),
            Transacao("Mercado X Loja 1", "18 NOV", "RS 15,99"),
            Transacao("Sergipe", "18 NOV", "RS 15,99"),
            Transacao("Alemão", "18 NOV", "RS 15,99"),
            Transacao("Quitanta da Marcia", "18 NOV", "RS 15,99"),
            Transacao("Mercado X Loja 1", "18 NOV", "RS 15,99"),
            Transacao("Sergipe", "18 NOV", "RS 15,99"),
            Transacao("Alemão", "18 NOV", "RS 15,99"),
            Transacao("Quitanta da Marcia", "18 NOV", "RS 15,99"),
            Transacao("Mercado X Loja 1", "18 NOV", "RS 15,99"),
            Transacao("Sergipe", "18 NOV", "RS 15,99"),
            Transacao("Alemão", "18 NOV", "RS 15,99"),
            Transacao("Quitanta da Marcia", "18 NOV", "RS 15,99"),
            Transacao("Mercado X Loja 1", "18 NOV", "RS 15,99"),
            Transacao("Sergipe", "18 NOV", "RS 15,99"),
            Transacao("Alemão", "18 NOV", "RS 15,99")


        )

        listaClientes.addAll(mutableListOf)

        val recyclerView = list_transacoes_recyclerview
        recyclerView.adapter = ListTransacoesAdapter(listaClientes, this)
    }
}
