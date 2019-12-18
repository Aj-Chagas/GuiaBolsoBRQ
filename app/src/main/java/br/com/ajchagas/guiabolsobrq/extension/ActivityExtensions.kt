@file:Suppress("NAME_SHADOWING")

package br.com.ajchagas.guiabolsobrq.extension

import android.app.Activity
import android.app.DatePickerDialog
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.ajchagas.guiabolsobrq.R
import kotlinx.android.synthetic.main.dialog_edita_apelido_conta.view.*
import java.util.*

fun Activity.mostraErro(mensagem: String) {
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_LONG
    ).show()
}

fun Activity.dataPicker(editText : EditText, ano : Int, mes : Int, dia : Int){
    DatePickerDialog(this,
        { _, ano, mes, dia ->
            val dataSelecionada = Calendar.getInstance()
            dataSelecionada.set(ano, mes, dia)
            editText.setText(dataSelecionada.formataPara("dd/MM/yyyy"))
        }
        , ano, mes, dia)
        .show()
}

fun Activity.alert(title:String, msg:String, botaoPositivo:String, botaoNegativo:String, acaoBotaoPositivo : () -> Unit = {}){
    var alertDialog = AlertDialog.Builder(this)
    alertDialog.setTitle(title)
    alertDialog.setMessage(msg)
    alertDialog.setPositiveButton(botaoPositivo) { _, _ ->
        acaoBotaoPositivo()
    }
    alertDialog.setNegativeButton(botaoNegativo) { _, _ ->
    }
    alertDialog.show()
}


