package br.com.ajchagas.guiabolsobrq.ui.activity

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.model.Data
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.CadastroContaActivityViewModel
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.factory.CadastroContaActivityViewModelFactory
import br.com.ajchagas.guiabolsobrq.validator.ValidacaoPadrao
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.toolbar.*


class CadastroContaActivity : AppCompatActivity() {

    private val validators : MutableList<ValidacaoPadrao> = mutableListOf()
    private val app_name = "Cadastro Conta"

    private val viewModel by lazy {
        val repository = Repository()
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
        viewModel.buscaListaBancos().observe(this, Observer {
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
        cadastro_botao_Salvar.setOnClickListener { view ->

            if (validaTodosOsCampos()) {
                Snackbar.make(view, "Implementa evento do botão", Snackbar.LENGTH_LONG).show()
            }
        }
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
        val adapter = ArrayAdapter<Data>(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            listBancos
        )
        spinnerBancos.adapter = adapter

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
                val idBanco = listBancos.get(position).id

                Toast.makeText(this@CadastroContaActivity, idBanco.toString(), Toast.LENGTH_LONG).show()
            }

        }
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
