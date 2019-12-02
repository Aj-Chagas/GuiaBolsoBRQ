package br.com.ajchagas.guiabolsobrq.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.database.AppDatabase
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.model.listaBancoApi.Data
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.CadastroContaActivityViewModel
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.factory.CadastroContaActivityViewModelFactory
import br.com.ajchagas.guiabolsobrq.validator.ValidacaoPadrao
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.properties.Delegates


class CadastroContaActivity : AppCompatActivity() {

    private val validators : MutableList<ValidacaoPadrao> = mutableListOf()
    private val app_name = "Cadastro Conta"

    private val contaId: Int by lazy {
        intent.getIntExtra("contaId", 0)
    }

    private var idDoBancoSelecionado by Delegates.notNull<String>()


    private val viewModel by lazy {
        val repository = Repository(AppDatabase.getInstance(this).contaDAO)
        val factory = CadastroContaActivityViewModelFactory(repository)
        val provedor = ViewModelProviders.of(this, factory)
        provedor.get(CadastroContaActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        configuraToolBar()
        buscaListaBancos()
        validaCamposPreenchido()
        configuraBotaoSalvar()
        configuraBotaoCancelar()

    }

    private fun buscaListaBancos() {
        viewModel.buscaListaBancosNaApi().observe(this, Observer {
            it?.dado?.let {
                val listaDeBancos = it.data
                configuraSpinner(listaDeBancos)
            }
            it?.erro?.let {
                Toast.makeText(this, "Deu Falha", Toast.LENGTH_LONG).show()
            }
        }
        )
    }

    private fun configuraBotaoCancelar() {
        cadastro_botao_cancelar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun configuraBotaoSalvar() {
        cadastro_botao_Salvar.setOnClickListener {

            if (validaTodosOsCampos()) {
                val apelido = cadastro_edit_text_apelido.text.toString()
                val titular = cadastro_edit_text_nome.text.toString()
                val agencia = cadastro_edit_text_agencia.text.toString()
                val conta = cadastro_edit_text_conta.text.toString()

                salva(Conta(contaId, idDoBancoSelecionado.toInt(), titular, apelido, agencia, conta))
            }
        }
    }

    private fun salva(conta: Conta) {
        viewModel.salva(conta).observe(this, Observer {
            if(it.erro == null){
                finish()
            }else{
                Toast.makeText(this, it.erro, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun validaTodosOsCampos() : Boolean{
        var estaValido = true
        for (validator in validators){
            if(!validator.estaValido()){
                estaValido = false
            }
        }
        return estaValido
    }

    private fun validaCamposPreenchido() {
        validaCampoObrigatorio(cadastro_textview_apelido_layout)
        validaCampoObrigatorio(cadastro_textview_nome_layout)
        validaCampoObrigatorio(cadastro_textview_agencia_layout)
        validaCampoObrigatorio(cadastro_textview_conta_layout)

    }

    private fun validaCampoObrigatorio(textInputLayout: TextInputLayout) {
        val validacaoPadrao = ValidacaoPadrao(textInputLayout)
        val editText = textInputLayout.editText
        validators.add(validacaoPadrao)
        editText?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                    validacaoPadrao.estaValido()
            }
        }
    }

    private fun configuraSpinner(listBancos : List<Data>) {
        val spinnerBancos = cadastro_spinner_bancos
        val adapter = configuraAdapterSpinner(listBancos)
        spinnerBancos.adapter = adapter

        spinnerClickListener(spinnerBancos, listBancos)
    }

    private fun spinnerClickListener(
        spinnerBancos: Spinner,
        listBancos: List<Data>
    ) {
        spinnerBancos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                this@CadastroContaActivity.idDoBancoSelecionado = listBancos[position].id.toString()

            }

        }
    }

    private fun configuraAdapterSpinner(listBancos: List<Data>): ArrayAdapter<Data> {
        return ArrayAdapter<Data>(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            listBancos
        )
    }


    private fun configuraToolBar() {
        val toolbarid = toolbarid2
        setSupportActionBar(toolbarid)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        colappsingtoolbar.title = app_name
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
