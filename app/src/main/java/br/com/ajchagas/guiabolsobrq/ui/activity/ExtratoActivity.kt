package br.com.ajchagas.guiabolsobrq.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.database.AppDatabase
import br.com.ajchagas.guiabolsobrq.extension.*
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

        val conta = DeserializacaoDaConta()
        bindViewConta(conta)
        configuraDatePickerDialog()
        configuraRecyclerView()
        buscaExtrato(conta, GregorianCalendar().formataPara("yyyyMMdd"), GregorianCalendar().ultimos30Dias().formataPara("yyyyMMdd"))
    }

    private fun bindViewConta(conta: Conta) {
        extrato_textview_nome_banco.text = conta.apelido
        extrato_textview_nome_titular.text = conta.titular
        extrato_textview_numero_agencia.text = conta.agencia
        extrato_textview_numero_conta.text = conta.numeroConta
        extrato_textview_saldo_total.text = conta.saldo.formataMoedaParaBrasileiro()
    }

    private fun DeserializacaoDaConta() = intent.getSerializableExtra("conta") as Conta

    private fun buscaExtrato(conta: Conta, dataInicio : String, dataFim : String ) {
        list_transacoes_progressBar.visibility = View.VISIBLE

        viewModel.buscaExtrato(conta, dataInicio, dataFim).observe(this, androidx.lifecycle.Observer  {

            list_transacoes_progressBar.visibility = View.GONE

            it?.dado?.let { Extrato ->

                if(Extrato.data.isNotEmpty()){
                    adapter.atualiza(Extrato.data)
                }else{
                    list_transacoes_textview_msgDeDadosNaoEncontrado.visibility = View.VISIBLE
                }
            }
            it?.erro?.let{
                mostraErro("Verifique a conexão com a internet")
            }
        })
    }

    private fun configuraDatePickerDialog() {
        val dataDe = extrato_edittext_input_data_de
        val dataAte = extrato_edittext_input_data_ate
        configuraCampoData(dataDe, GregorianCalendar().ultimos30Dias())
        configuraCampoData(dataAte, GregorianCalendar())
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

    private fun configuraCampoData(campoData : EditText, hoje : Calendar) {

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData.setText(hoje.formataPara("dd/MM/yyyy"))
        campoData.setOnClickListener {

            dataPicker(campoData, ano, mes, dia)
        }
    }
}
