package br.com.ajchagas.guiabolsobrq.extension

fun String.apenasAprimeiraLetraMaiuscula() : String {
    return this.substring(0,1).toUpperCase() +
            this.substring(1).toLowerCase()
}

fun String.formataDataString() : String{
    val dataQuebradaDe: List<String> = this.split("/")
    val dia = dataQuebradaDe[0]
    val mes = dataQuebradaDe[1]

    val mesInt = Integer.valueOf(mes)
    var mesFormatado = ""
    mesFormatado = when (mesInt) {
        1 -> "JAN  "
        2 -> "FEV  "
        3 -> "MAR  "
        4 -> "ABR  "
        5 -> "MAI  "
        6 -> "JUN  "
        7 -> "JUL  "
        8 -> "AGO  "
        9 -> "SET  "
        10 -> "OUT  "
        11 -> "NOV  "
        12 -> "DEZ  "
        else -> "Invalid month"
    }
    return "$dia $mesFormatado"
}