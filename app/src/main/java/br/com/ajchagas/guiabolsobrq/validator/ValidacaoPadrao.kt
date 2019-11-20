package br.com.ajchagas.guiabolsobrq.validator

import com.google.android.material.textfield.TextInputLayout

class ValidacaoPadrao(
    private val textInputLayout : TextInputLayout
) {
    private val MSG_DE_ERRO = "Campo Obrigatório"

    private fun validaCampoObrigatorio() : Boolean{
        val editText = textInputLayout.editText
        val text = editText?.text
        if(text?.isEmpty()!!){
            textInputLayout.error = MSG_DE_ERRO
            return false
        }
        removeErro()
        return true
    }

    private fun removeErro(){
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }

    fun estaValido() : Boolean {
        return validaCampoObrigatorio()
    }
}