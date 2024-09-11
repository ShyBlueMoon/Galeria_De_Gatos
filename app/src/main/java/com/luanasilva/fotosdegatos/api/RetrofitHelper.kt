package com.luanasilva.fotosdegatos.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {
        const val BASE_URL = "https://api.thecatapi.com/"
        const val BASE_URL_IMAGEM = "https://cdn2.thecatapi.com/images/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val gatoAPI = retrofit.create(GatoAPI::class.java)

    }
}