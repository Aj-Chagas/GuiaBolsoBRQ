package br.com.ajchagas.guiabolsobrq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.ajchagas.guiabolsobrq.R

class CadastroContaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
