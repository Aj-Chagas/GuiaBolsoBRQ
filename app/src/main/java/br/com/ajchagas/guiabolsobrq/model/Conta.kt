package br.com.ajchagas.guiabolsobrq.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.math.BigDecimal

@Entity
data class Conta(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val idBanco  : Int,
    val titular: String,
    var apelido : String,
    val agencia : String,
    val numeroConta  : String,
    val saldo : BigDecimal = BigDecimal(0.0)) : Serializable
