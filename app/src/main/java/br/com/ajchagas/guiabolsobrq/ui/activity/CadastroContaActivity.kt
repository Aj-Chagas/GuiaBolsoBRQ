package br.com.ajchagas.guiabolsobrq.ui.activity

import android.graphics.Typeface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.validator.ValidacaoPadrao
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.toolbar.*


class CadastroContaActivity : AppCompatActivity() {

    private val validators : MutableList<ValidacaoPadrao> = mutableListOf()
    private val app_name = "Cadastro Conta"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        configuraToolBar()
        configuraSpinner()
        validaCamposPreenchido()
        configuraBotaoSalvar()
        configuraBotaoCancelar()

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

    private fun configuraSpinner() {
        val spinnerBancos = cadastro_spinner_bancos
        val listBancos = spinnerBancos.resources.getStringArray(R.array.lista_bancos)
        spinnerBancos.adapter =
            ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                listBancos)
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
