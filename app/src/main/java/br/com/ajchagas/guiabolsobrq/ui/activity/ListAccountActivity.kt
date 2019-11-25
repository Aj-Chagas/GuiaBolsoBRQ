package br.com.ajchagas.guiabolsobrq.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.extension.formataMoedaParaBrasileiro
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
        configuraClickDoCard()
        configuraFAB()
        somaSaldoTotal()
    }

    private fun somaSaldoTotal() {
        var saldo: BigDecimal = BigDecimal.ZERO
        for (conta: Conta in listaContas) {
            saldo += conta.saldo
        }
        item_saldo_total_valor.text = saldo.formataMoedaParaBrasileiro()
    }

    private fun configuraClickDoCard() {
        val adapter = ListAccountAdapter(listaContas, this, clickListener = { contaClicada ->
            val vaiParaExtrato = Intent(this, ExtratoActivity::class.java)
            vaiParaExtrato.putExtra("conta", contaClicada)
            startActivity(vaiParaExtrato)
        })

        list_account_recyclerview.adapter = adapter
    }

    private fun listaContasParaTeste() {
        val listaDeContas = mutableListOf<Conta>(
            Conta(
                apelido = "Itaú",
                agencia = "1220",
                numeroConta = "23177-5",
                saldo = BigDecimal(53957.33)
            ), Conta(
                apelido = "Santander",
                agencia = "1320",
                numeroConta = "10000-5",
                saldo = BigDecimal(9875.29)
            ),
            Conta(
                apelido = "Nubank",
                agencia = "0001",
                numeroConta = "10320-5",
                saldo = BigDecimal(75432.19)
            ),
            Conta(
                apelido = "Itaú",
                agencia = "1220",
                numeroConta = "23177-5",
                saldo = BigDecimal(42500.00)
            ), Conta(
                apelido = "Itaú",
                agencia = "1220",
                numeroConta = "23177-5",
                saldo = BigDecimal(53957.33)
            ), Conta(
                apelido = "Santander",
                agencia = "1320",
                numeroConta = "10000-5",
                saldo = BigDecimal(9875.29)
            ),
            Conta(
                apelido = "Nubank",
                agencia = "0001",
                numeroConta = "10320-5",
                saldo = BigDecimal(75432.19)
            ),
            Conta(
                apelido = "Itaú",
                agencia = "1220",
                numeroConta = "23177-5",
                saldo = BigDecimal(42500.00)
            )
        )

        listaContas.addAll(listaDeContas)
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
