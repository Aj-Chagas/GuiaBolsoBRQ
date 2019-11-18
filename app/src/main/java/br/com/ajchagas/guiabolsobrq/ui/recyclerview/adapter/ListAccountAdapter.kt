package br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.model.Conta
import kotlinx.android.synthetic.main.item_conta.view.*

class ListAccountAdapter(
    private val contas: MutableList<Conta>,
    private val context: Context
) : RecyclerView.Adapter<ListAccountAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewCriada = LayoutInflater.from(context)
            .inflate(R.layout.item_conta, parent, false)
        return ViewHolder(viewCriada)
    }

    override fun getItemCount(): Int {
        return contas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conta = contas[position]

        holder.bindView(conta)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        fun bindView(conta: Conta) {


            val apelido = itemView.item_conta_apelido
            val agencia = itemView.item_conta_textview_agencia
            val numeroConta = itemView.item_conta_textview_numero_conta
            val saldo = itemView.item_conta_textview_saldo

            apelido.text = conta.apelido
            agencia.text = conta.agencia
            numeroConta.text = conta.numeroConta
            saldo.text = conta.saldo.toString()
        }

    }

}

