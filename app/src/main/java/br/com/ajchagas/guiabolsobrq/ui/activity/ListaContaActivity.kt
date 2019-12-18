package br.com.ajchagas.guiabolsobrq.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.database.AppDatabase
import br.com.ajchagas.guiabolsobrq.extension.alert
import br.com.ajchagas.guiabolsobrq.extension.formataMoedaParaBrasileiro
import br.com.ajchagas.guiabolsobrq.extension.mostraErro
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.model.listaBancoApi.Data
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.ui.dialog.DialogListaContaActivity
import br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter.ListAccountAdapter
import br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter.ListTransacoesAdapter
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.ExtratoViewModel
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.ListaContaViewModel
import kotlinx.android.synthetic.main.activity_extrato.*
import kotlinx.android.synthetic.main.activity_list_account.*
import kotlinx.android.synthetic.main.activity_list_account.colappsingtoolbar
import kotlinx.android.synthetic.main.dialog_edita_apelido_conta.view.*
import kotlinx.android.synthetic.main.recycler_view_list_account.*
import kotlinx.android.synthetic.main.recyclerview_list_transacoes.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*
import java.math.BigDecimal


class ListaContaActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var listaContas: List<Conta>

    private val viewmodel by lazy {
        val repository = Repository(AppDatabase.getInstance(this).contaDAO)
        ViewModelProviders.of(this, ListaContaViewModel.FACTORY(repository))
            .get(ListaContaViewModel::class.java)
    }

    private val adapter by lazy {
        ListAccountAdapter(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_account)

        val toolbar = findViewById<Toolbar>(R.id.toolbarid2)
        setSupportActionBar(toolbar)
        configuraClickDoCard()
        configuraFAB()
        buscaTodasContas()
        somaSaldoTotal()
    }

    private fun somaSaldoTotal() {
        viewmodel.buscaTodos().observe(this, Observer { listaDeContasCadastradas ->
            if (listaDeContasCadastradas != null) {
                var saldo: BigDecimal = BigDecimal.ZERO
                for (conta: Conta in listaDeContasCadastradas) {
                    saldo += conta.saldo
                }
                item_saldo_total_valor.text = saldo.formataMoedaParaBrasileiro()
            }
        })
    }

    private fun buscaTodasContas() {
        viewmodel.buscaTodos().observe(this, Observer { listaDeContasCadastradas ->

            if (listaDeContasCadastradas != null) {
                listaContas = listaDeContasCadastradas
            }

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

    private fun abreActivityExtrato(contaClicada: Conta) {
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

        if (item.groupId == 0) {
            showAlertRemoverConta(item)
        } else {
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
        alert(title = "Remover",
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


    private fun getContaSelecionada(item: MenuItem): Conta {
        val position = item.order
        return adapter.getItem(position)

    }

    private fun remove(conta: Conta) {
        viewmodel.remove(conta)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setQueryHint("Procurar...")

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {

        val entradaDoUsuario = newText.toLowerCase()
        var contaDigitada = mutableListOf<Conta>()

        for (conta: Conta in listaContas) {

            if (conta.nomebanco.toLowerCase().contains(entradaDoUsuario) ||
                conta.apelido.toLowerCase().contains(entradaDoUsuario) ||
                conta.titular.toLowerCase().contains(entradaDoUsuario) ||
                conta.agencia.toLowerCase().contains(entradaDoUsuario) ||
                conta.numeroConta.toLowerCase().contains(entradaDoUsuario)
            ) {
                contaDigitada.add(conta)
            }
        }
        adapter.atualiza(contaDigitada)
        var saldo: BigDecimal = BigDecimal.ZERO
        for (conta: Conta in contaDigitada) {
            saldo += conta.saldo
        }
        item_saldo_total_valor.text = saldo.formataMoedaParaBrasileiro()

        return true
    }
}

