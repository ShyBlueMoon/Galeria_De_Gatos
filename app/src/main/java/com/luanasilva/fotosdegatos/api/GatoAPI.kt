package com.luanasilva.fotosdegatos.api

import com.luanasilva.fotosdegatos.model.GatoResposta
import retrofit2.Response
import retrofit2.http.GET

interface GatoAPI {

    @GET("v1/images/search?limit=10")
    suspend fun recuperarImagensGatos(): Response<GatoResposta>
}