package br.com.ajchagas.guiabolsobrq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.ajchagas.guiabolsobrq.R
import kotlinx.android.synthetic.main.collapsing_toolbar.*

class CadastroContaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        configuraToolBar()


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
