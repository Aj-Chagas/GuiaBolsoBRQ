package br.com.ajchagas.guiabolsobrq.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.model.TipoTransacao
import br.com.ajchagas.guiabolsobrq.model.Transacao
import kotlinx.android.synthetic.main.extrato_item_transacao.view.*

class ListTransacoesAdapter(
    private val listaTransacoes: MutableList<Transacao>,
    private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.extrato_item_transacao, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaTransacoes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val transacao = listaTransacoes[position]
        holder.bindView(transacao)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    fun RecyclerView.ViewHolder.bindView(transacao: Transacao) {
        val nome = itemView.list_transacoes_nome_transacao
        val data = itemView.list_transacoes_data_transacao
        val valor= itemView.list_transacoes_valor_transacao

        nome.text = transacao.nome
        data.text = transacao.data
        if(transacao.tipo == TipoTransacao.Credito){
            valor.text = transacao.valor
        }else{
            valor.text = "- " + transacao.valor
        }


    }

}
