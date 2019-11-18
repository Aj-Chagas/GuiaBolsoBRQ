package br.com.ajchagas.guiabolsobrq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import br.com.ajchagas.guiabolsobrq.R
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.toolbar.*

class CadastroContaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        configuraToolBar()
        val spinnerBancos = cadastro_spinner_bancos
        val listBancos = spinnerBancos.resources.getStringArray(R.array.lista_bancos)
        spinnerBancos.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listBancos)

    }

    private fun configuraToolBar() {
        val toolbarid = toolbarid2
        setSupportActionBar(toolbarid)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        colappsingtoolbar.title = "Cadastro Conta"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
