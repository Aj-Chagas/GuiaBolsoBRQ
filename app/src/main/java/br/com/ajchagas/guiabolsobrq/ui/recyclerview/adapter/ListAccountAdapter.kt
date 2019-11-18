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
    private val contas: MutableList<Conta> = mutableListOf(),
    private val context: Context,
    var clickListener: (Conta) -> Unit = {}
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

        holder.bindView(conta, clickListener)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        fun bindView(conta: Conta, cliclListener: (Conta) -> Unit) {

            itemView.item_conta_apelido.text = conta.apelido
            itemView.item_conta_textview_agencia.text = conta.agencia
            itemView.item_conta_textview_numero_conta.text = conta.numeroConta
            itemView.item_conta_textview_saldo.text = conta.saldo.toString()
            itemView.setOnClickListener { cliclListener(conta) }

        }

    }

}

