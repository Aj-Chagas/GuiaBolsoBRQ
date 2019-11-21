package br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.extension.formataMoedaParaBrasileiro
import br.com.ajchagas.guiabolsobrq.model.Conta
import kotlinx.android.synthetic.main.item_conta.view.*

class ListAccountAdapter(
    private val contas: MutableList<Conta> = mutableListOf(),
    private val context: Context,
    val clickListener: (Conta) -> Unit = {},
    val longClickListener: (Conta) -> Boolean = {false}
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
        val contaSeleccionada = contas[position]

        holder.bindView(contaSeleccionada, clickListener, longClickListener)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        fun bindView(
            contaSelecionada: Conta,
            cliclListener: (Conta) -> Unit,
            longClickListener: (Conta) -> Boolean
        ) {

            itemView.item_conta_apelido.text = contaSelecionada.apelido
            itemView.item_conta_textview_agencia.text = contaSelecionada.agencia
            itemView.item_conta_textview_numero_conta.text = contaSelecionada.numeroConta
            itemView.item_conta_textview_saldo.text = contaSelecionada.saldo.formataMoedaParaBrasileiro()
            itemView.setOnClickListener { cliclListener(contaSelecionada) }
            itemView.setOnLongClickListener{ longClickListener(contaSelecionada) }
        }

    }

}

