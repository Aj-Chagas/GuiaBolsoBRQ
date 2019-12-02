package br.com.ajchagas.guiabolsobrq.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.database.AppDatabase
import br.com.ajchagas.guiabolsobrq.extension.formataMoedaParaBrasileiro
import br.com.ajchagas.guiabolsobrq.extension.formataParaBrasileiro
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter.ListTransacoesAdapter
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.ExtratoViewModel
import kotlinx.android.synthetic.main.activity_extrato.*
import kotlinx.android.synthetic.main.recyclerview_list_transacoes.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class ExtratoActivity : AppCompatActivity() {


    private val adapter by lazy {
        ListTransacoesAdapter( context = this)
    }

    private val viewModel by lazy{
        val repository = Repository(AppDatabase.getInstance(this).contaDAO)
        ViewModelProviders.of(this, ExtratoViewModel.FACTORY(repository)).get(ExtratoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extrato)

        configuraToolBar()

        val conta = intent.getSerializableExtra("conta") as Conta
        extrato_textview_nome_banco.text = conta.apelido
        extrato_textview_nome_titular.text = conta.titular
        extrato_textview_numero_agencia.text = conta.agencia
        extrato_textview_numero_conta.text = conta.numeroConta
        extrato_textview_saldo_total.text = conta.saldo.formataMoedaParaBrasileiro()

        configuraDatePickerDialog()
        configuraRecyclerView()

        viewModel.buscaExtrato(conta).observe(this, androidx.lifecycle.Observer {
            Toast.makeText(this, "Evento recebido com sucesso", Toast.LENGTH_LONG).show()
            it?.dado?.let {Extrato ->
                val listaDeTransacoes = Extrato.data
                adapter.atualiza(listaDeTransacoes)
            }
        })
    }

    private fun configuraDatePickerDialog() {
        val dataDe = extrato_edittext_input_data_de
        val dataAte = extrato_edittext_input_data_ate
        configuraCampoData(dataDe)
        configuraCampoData(dataAte)
    }

    private fun configuraRecyclerView() {
        val recyclerView = list_transacoes_recyclerview
        recyclerView.adapter = adapter
    }

    private fun configuraToolBar() {
        val toolbarid = toolbarid2
        setSupportActionBar(toolbarid)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        colappsingtoolbar.title = "Detalhes"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun configuraCampoData(campoData : EditText) {
        val hoje = Calendar.getInstance()

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData.setText(hoje.formataParaBrasileiro())
        campoData.setOnClickListener {
            DatePickerDialog(this,
                { _, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)
                    campoData.setText(dataSelecionada.formataParaBrasileiro())
                }
                , ano, mes, dia)
                .show()
        }
    }
}
