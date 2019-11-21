package br.com.ajchagas.guiabolsobrq.model

import java.io.Serializable
import java.math.BigDecimal

class Conta(val apelido : String,
            val agencia : String,
            val numeroConta  : String,
            val saldo : BigDecimal) : Serializable
