package br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.extension.apenasAprimeiraLetraMaiuscula
import br.com.ajchagas.guiabolsobrq.extension.formataMoedaParaBrasileiro
import br.com.ajchagas.guiabolsobrq.model.Conta
import kotlinx.android.synthetic.main.item_conta.view.*

class ListAccountAdapter(
    private val contas: MutableList<Conta> = mutableListOf(),
    private val context: Context,
    var clickListener: (Conta) -> Unit = {}
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
        holder.bindView(contaSeleccionada, clickListener)
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

            menu?.setHeaderTitle("Escolha uma ação: ")
            menu?.add(0, v!!.id, adapterPosition, "Remover")
            menu?.add(1, v!!.id, adapterPosition, "Editar apelido")
        }

        fun bindView(
            contaSelecionada: Conta,
            cliclListener: (Conta) -> Unit

        ) {
            bindCampoApelido(contaSelecionada)
            bindCampoNomeDoBanco(contaSelecionada)
            bindCampoAgencia(contaSelecionada)
            bindCampoNumeroConta(contaSelecionada)
            bindCampoSaldo(contaSelecionada)
            itemView.setOnClickListener { cliclListener(contaSelecionada) }
            itemView.setOnCreateContextMenuListener(this)
        }

        private fun bindCampoSaldo(contaSelecionada: Conta) {
            itemView.item_conta_textview_saldo.text =
                contaSelecionada.saldo.formataMoedaParaBrasileiro()
        }

        private fun bindCampoNumeroConta(contaSelecionada: Conta) {
            itemView.item_conta_textview_numero_conta.text = contaSelecionada.numeroConta
        }

        private fun bindCampoAgencia(contaSelecionada: Conta) {
            itemView.item_conta_textview_agencia.text = contaSelecionada.agencia
        }

        private fun bindCampoNomeDoBanco(contaSelecionada: Conta) {
            itemView.item_conta_textview_nome_banco.text = contaSelecionada.nomebanco.apenasAprimeiraLetraMaiuscula()
        }

        private fun bindCampoApelido(contaSelecionada: Conta) {
            itemView.item_conta_apelido.text = contaSelecionada.apelido
        }


    }

}

