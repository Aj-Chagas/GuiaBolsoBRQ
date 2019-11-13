package br.com.ajchagas.guiabolsobrq.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter.ListAccountAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list_account.*
import kotlinx.android.synthetic.main.recycler_view_list_account.*
import java.math.BigDecimal

class ListAccountActivity : AppCompatActivity() {

    private val listaContas : MutableList<Conta> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_account)
        setSupportActionBar(toolbar)
        title = "Conta"

        val mutableListOf = mutableListOf<Conta>(
            Conta(
                apelido = "Itaú",
                agencia = "1220",
                numeroConta = "23177-5",
                saldo = BigDecimal(1000.00)
            ), Conta(
                apelido = "Santander",
                agencia = "1320",
                numeroConta = "10000-5",
                saldo = BigDecimal(3000.00)
            ),
            Conta(
                apelido = "Nubank",
                agencia = "0001",
                numeroConta = "10320-5",
                saldo = BigDecimal(7000.00)),
            Conta(
                apelido = "Itaú",
                agencia = "1220",
                numeroConta = "23177-5",
                saldo = BigDecimal(1000.00)
            ), Conta(
                apelido = "Santander",
                agencia = "1320",
                numeroConta = "10000-5",
                saldo = BigDecimal(3000.00)
            ),
            Conta(
                apelido = "Nubank",
                agencia = "0001",
                numeroConta = "10320-5",
                saldo = BigDecimal(7000.00)),
            Conta(
                apelido = "Itaú",
                agencia = "1220",
                numeroConta = "23177-5",
                saldo = BigDecimal(1000.00)
            ), Conta(
                apelido = "Santander",
                agencia = "1320",
                numeroConta = "10000-5",
                saldo = BigDecimal(3000.00)
            ),
            Conta(
                apelido = "Nubank",
                agencia = "0001",
                numeroConta = "10320-5",
                saldo = BigDecimal(7000.00))
        )

        listaContas.addAll(mutableListOf)

        val recyclerView = list_account_recyclerview
        recyclerView.adapter = ListAccountAdapter(listaContas, this)



        configuraFAB()
    }

    private fun configuraFAB() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


}