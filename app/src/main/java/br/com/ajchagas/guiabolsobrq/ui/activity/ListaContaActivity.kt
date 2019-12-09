package br.com.ajchagas.guiabolsobrq.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.database.AppDatabase
import br.com.ajchagas.guiabolsobrq.extension.alert
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.ui.dialog.DialogListaContaActivity
import br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter.ListAccountAdapter
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.ListaContaViewModel
import kotlinx.android.synthetic.main.activity_list_account.*
import kotlinx.android.synthetic.main.dialog_edita_apelido_conta.view.*
import kotlinx.android.synthetic.main.recycler_view_list_account.*
import org.koin.android.ext.android.inject


class ListaContaActivity : AppCompatActivity() {

    private val viewmodel by inject<ListaContaViewModel>()

    private val adapter by lazy {
        ListAccountAdapter(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_account)

        title = "Conta"

        configuraClickDoCard()
        configuraFAB()
        buscaTodasContas()
    }

    private fun buscaTodasContas() {
        viewmodel.buscaTodos().observe(this, Observer {listaDeContasCadastradas ->

            if (listaDeContasCadastradas != null) {
                adapter.atualiza(listaDeContasCadastradas)
            }
        })
    }

    private fun configuraClickDoCard() {
        list_account_recyclerview.adapter = adapter
        adapter.clickListener = this::abreActivityExtrato
        registerForContextMenu(list_account_recyclerview)
    }

    private fun  abreActivityExtrato(contaClicada: Conta) {
        val vaiParaExtrato = Intent(this, ExtratoActivity::class.java)
        vaiParaExtrato.putExtra("conta", contaClicada)
        startActivity(vaiParaExtrato)
    }

    private fun configuraFAB() {
        fab.setOnClickListener {
            abreActivityCadastroConta()
        }
    }

    private fun abreActivityCadastroConta() {
        val vaiParaActivityCadastroConta = Intent(this, CadastroContaActivity::class.java)
        startActivity(vaiParaActivityCadastroConta)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        if(item.groupId == 0){
            showAlertRemoverConta(item)
        } else{
            showAlertEditarConta(item)
        }

        return super.onContextItemSelected(item)
    }

    private fun showAlertEditarConta(item: MenuItem) {
        val contaSelecionada = getContaSelecionada(item)
        DialogListaContaActivity(this)
            .criaDialogParaEditarApelido(item, contaSelecionada, acaoPositiva = { viewCriada ->
                editaConta(viewCriada, contaSelecionada)
            })
    }

    private fun showAlertRemoverConta(item: MenuItem) {
        alert(title = "remover",
            msg = "Tem certeza que deseja remover?",
            botaoPositivo = "Sim",
            botaoNegativo = "Não",
            acaoBotaoPositivo = {
                val contaSelecionada = getContaSelecionada(item)
                remove(contaSelecionada)
            })
    }

    private fun editaConta(
        viewCriada: View,
        conta: Conta
    ) {
        val novoApelido = viewCriada.dialog_edita_apelido_text.text
        conta.apelido = novoApelido.toString()
        edita(conta)
    }

    private fun edita(conta: Conta) {
        viewmodel.edita(conta)
    }


    private fun getContaSelecionada(item: MenuItem) : Conta{
        val position = item.order
        return adapter.getItem(position)

    }

    private fun remove(conta: Conta) {
        viewmodel.remove(conta)
    }
}

