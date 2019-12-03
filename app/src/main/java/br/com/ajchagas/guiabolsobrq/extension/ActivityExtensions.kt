package br.com.ajchagas.guiabolsobrq.extension

import android.app.Activity
import android.app.DatePickerDialog
import android.widget.EditText
import android.widget.Toast
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
            editText.setText(dataSelecionada.formataParaBrasileiro())
        }
        , ano, mes, dia)
        .show()
}