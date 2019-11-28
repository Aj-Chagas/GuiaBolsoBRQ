package br.com.ajchagas.guiabolsobrq.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id") val id: Int,
    @SerializedName("codigo") val codigo: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("agencia") val agencia : Int,
    @SerializedName("conta") val conta : Int,
    @SerializedName("conta_dig") val conta_dig : Int,
    @SerializedName("data_alteracao") val data_alteracao: String,
    @SerializedName("hora_alteracadao") val hora_alteracadao : String,
    @SerializedName("data_criacao") val data_criacao: String,
    @SerializedName("hora_criacao") val hora_criacao: String
){

    override fun toString() : String{
        return nome
    }
}
