package br.com.ajchagas.guiabolsobrq.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.ajchagas.guiabolsobrq.R
import br.com.ajchagas.guiabolsobrq.model.Conta
import kotlinx.android.synthetic.main.dialog_edita_apelido_conta.view.*

class DialogListaContaActivity(
    private val context : Context
) {

    fun criaDialogParaEditarApelido(item: MenuItem,
                                    conta : Conta,
                                    acaoPositiva: (viewCriada : View) -> Unit = {},
                                    acaoNegativa: () -> Unit = {}) {

        this?.let {

            val builder = AlertDialog.Builder(context)
            val inflater = LayoutInflater.from(context)

            val viewCriada = inflater.inflate(R.layout.dialog_edita_apelido_conta, null)
            builder.setView(viewCriada)

                .setPositiveButton("Editar") { dialog, id ->
                    acaoPositiva(viewCriada)
                }
                .setNegativeButton("Cancelar") { dialog, id ->
                    dialog.dismiss()
                }
            viewCriada.dialog_edita_apelido_text.setText(conta.apelido)
            builder.create().show()
        }
    }
}