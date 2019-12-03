package br.com.ajchagas.guiabolsobrq.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.database.AppDatabase
import br.com.ajchagas.guiabolsobrq.model.Conta
import br.com.ajchagas.guiabolsobrq.repository.Repository
import br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter.ListAccountAdapter
import br.com.ajchagas.guiabolsobrq.ui.viewmodel.ListaContaViewModel
import kotlinx.android.synthetic.main.activity_list_account.*
import kotlinx.android.synthetic.main.recycler_view_list_account.*


class ListaContaActivity : AppCompatActivity() {

    private val viewmodel by lazy{
        val repository = Repository(AppDatabase.getInstance(this).contaDAO)
        ViewModelProviders.of(this, ListaContaViewModel.FACTORY(repository)).get(ListaContaViewModel::class.java)
    }

    private val adapter by lazy {
        ListAccountAdapter(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_account)

        title = "Conta"

        configuraClickDoCard()
        configuraFAB()
        buscaTodasContas()


        //somaSaldoTotal()
    }

    private fun buscaTodasContas() {
        viewmodel.buscaTodos().observe(this, Observer {listaDeContasCadastradas ->

            if (listaDeContasCadastradas != null) {
                adapter.atualiza(listaDeContasCadastradas)
            }
        })
    }

//    private fun somaSaldoTotal() {
//        var saldo: BigDecimal = BigDecimal.ZERO
//        for (conta: Conta in listaContas) {
//            saldo += conta.saldo
//        }
//        item_saldo_total_valor.text = saldo.formataMoedaParaBrasileiro()
//    }

    private fun configuraClickDoCard() {
        list_account_recyclerview.adapter = adapter
        adapter.clickListener = this::abreActivityExtrato
        registerForContextMenu(list_account_recyclerview)

    }

    private fun  abreActivityExtrato(contaClicada: Conta) {
        val vaiParaExtrato = Intent(this, ExtratoActivity::class.java)
        vaiParaExtrato.putExtra("conta", contaClicada)
        startActivity(vaiParaExtrato)
    }

    private fun configuraFAB() {
        fab.setOnClickListener {
            abreActivityCadastroConta()
        }
    }

    private fun abreActivityCadastroConta() {
        val vaiParaActivityCadastroConta = Intent(this, CadastroContaActivity::class.java)
        startActivity(vaiParaActivityCadastroConta)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Remover")
        alertDialog.setMessage("Deseja remover esta conta ?")
        alertDialog.setPositiveButton("Sim") { _, _ ->

            val position = item.order
            val conta = adapter.getItem(position)
            remove(conta)

        }

        alertDialog.setNegativeButton("Não") { _, _ ->
            Toast.makeText(this, "Não", Toast.LENGTH_LONG).show()
        }
        alertDialog.show()

        return super.onContextItemSelected(item)
    }

    private fun remove(conta: Conta) {
        viewmodel.remove(conta)
    }
}

