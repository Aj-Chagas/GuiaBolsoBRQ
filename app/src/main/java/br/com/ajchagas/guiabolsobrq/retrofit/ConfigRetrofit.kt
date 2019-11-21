package br.com.ajchagas.guiabolsobrq.retrofit

import br.com.ajchagas.guiabolsobrq.retrofit.service.ServiceBancos
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigRetrofit {

    private val BASE_URL: String = "http://api.rlmsolutions.com.br/"

    private val client by lazy{
        val interceptador = HttpLoggingInterceptor()
        interceptador.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptador)
            .build()
    }


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val serviceBancos : ServiceBancos by lazy {
        retrofit.create(ServiceBancos::class.java)
    }
}