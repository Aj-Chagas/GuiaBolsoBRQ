package br.com.ajchagas.guiabolsobrq.repository

open class Resource<T>(
    val dado: T?,
    val erro: String? = null
)

fun<T> criaResourceDeFalha(
    resultadoAtual: Resource<T>?,
    msgErro: String
): Resource<T>? {

    if (resultadoAtual != null) {
        return Resource(dado = resultadoAtual.dado, erro = msgErro)
    }
    return Resource(dado = null, erro = msgErro)
}