package br.com.ajchagas.guiabolsobrq.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter.ListAccountAdapter
import kotlinx.android.synthetic.main.activity_list_account.*
import kotlinx.android.synthetic.main.recycler_view_list_account.*
import java.math.BigDecimal

class ListAccountActivity : AppCompatActivity() {

    private val listaContas: MutableList<Conta> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_account)

        title = "Conta"


        listaContasParaTeste()

        val adapter = ListAccountAdapter(listaContas, this, clickListener = { contaClicada ->
            val vaiParaExtrato = Intent(this, ExtratoActivity::class.java)
            vaiParaExtrato.putExtra("conta", contaClicada)
            startActivity(vaiParaExtrato)
        }, longClickListener = {
            Toast.makeText(this, "funciona", Toast.LENGTH_LONG).show()
            true
        })
        list_account_recyclerview.adapter = adapter

        configuraFAB()
    }

    private fun listaContasParaTeste() {
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
                saldo = BigDecimal(7000.00)
            ),
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
                saldo = BigDecimal(7000.00)
            ),
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
                saldo = BigDecimal(7000.00)
            )
        )

        listaContas.addAll(mutableListOf)
    }

    private fun configuraFAB() {
        fab.setOnClickListener { view ->
            abreActivityCadastroConta()
        }
    }

    private fun abreActivityCadastroConta() {
        val vaiParaActivityCadastroConta = Intent(this, CadastroContaActivity::class.java)
        startActivity(vaiParaActivityCadastroConta)
    }

}
