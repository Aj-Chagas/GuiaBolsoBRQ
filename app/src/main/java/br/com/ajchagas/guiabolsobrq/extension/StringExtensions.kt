package br.com.ajchagas.guiabolsobrq.extension

fun String.apenasAprimeiraLetraMaiuscula() : String {
    return this.substring(0,1).toUpperCase() +
            this.substring(1).toLowerCase()
}