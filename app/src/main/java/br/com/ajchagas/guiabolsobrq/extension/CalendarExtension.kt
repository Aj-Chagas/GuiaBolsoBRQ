package br.com.ajchagas.guiabolsobrq.extension

import java.text.SimpleDateFormat
import java.util.*
//"dd-MMM"
fun Calendar.formataPara(formato : String) : String{
    val format = SimpleDateFormat(formato)
    return format.format(this.time)

}



fun Calendar.ultimos30Dias(): Calendar {
    this.add(Calendar.DAY_OF_MONTH, -30)
    return this
}



