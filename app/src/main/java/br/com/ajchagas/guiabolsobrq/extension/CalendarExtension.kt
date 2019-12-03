package br.com.ajchagas.guiabolsobrq.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro() : String{
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}

fun Calendar.ultimos30Dias(): Calendar {
    this.add(Calendar.DAY_OF_MONTH, -30)
    return this
}

fun Calendar.formataParayyyyMMdd() : String{
    val formatoBrasileiro = "yyyyMMdd"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}