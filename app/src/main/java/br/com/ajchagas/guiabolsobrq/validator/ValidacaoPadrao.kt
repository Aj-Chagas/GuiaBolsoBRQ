package br.com.ajchagas.guiabolsobrq.validator

import com.google.android.material.textfield.TextInputLayout

class ValidacaoPadrao(
    private val textInputLayout : TextInputLayout
) {
    private fun validaCampoObrigatorio() : Boolean{
        val editText = textInputLayout.editText
        val text = editText?.text
        if(text?.isEmpty()!!){
             textInputLayout.setError("Campo Obrigatório")
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
        val validaCampoObrigatorio = validaCampoObrigatorio()
        return validaCampoObrigatorio
    }
}