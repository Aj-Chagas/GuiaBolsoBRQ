package br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter

import android.content.Context
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.extension.formataMoedaParaBrasileiro
import br.com.ajchagas.guiabolsobrq.model.Conta
import kotlinx.android.synthetic.main.item_conta.view.*




class ListAccountAdapter(
    private val contas: MutableList<Conta> = mutableListOf(),
    private val context: Context,
    var clickListener: (Conta) -> Unit = {},
    var clickLongLister : (Conta) -> Boolean = {false}
) : RecyclerView.Adapter<ListAccountAdapter.ViewHolder>() {

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

        holder.bindView(contaSeleccionada, clickListener, clickLongLister)
    }

    fun atualiza(contas: List<Conta>) {
        notifyItemRangeRemoved(0, this.contas.size)
        this.contas.clear()
        this.contas.addAll(contas)
        notifyItemRangeInserted(0, this.contas.size)
    }

    fun getItem(position: Int): Conta {
        return contas[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnCreateContextMenuListener{

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {


        }

        fun bindView(
            contaSelecionada: Conta,
            cliclListener: (Conta) -> Unit,
            clickLongLister : (Conta) -> Boolean
        ) {

            itemView.item_conta_apelido.text = contaSelecionada.apelido
            itemView.item_conta_textview_agencia.text = contaSelecionada.agencia
            itemView.item_conta_textview_numero_conta.text = contaSelecionada.numeroConta
            itemView.item_conta_textview_saldo.text = contaSelecionada.saldo.formataMoedaParaBrasileiro()
            itemView.setOnClickListener { cliclListener(contaSelecionada) }
            itemView.setOnLongClickListener { clickLongLister(contaSelecionada) }
            itemView.setOnCreateContextMenuListener(this)
        }


    }

}

