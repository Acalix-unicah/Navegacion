package com.example.navegacion.data.client

import com.example.navegacion.data.model.CharacterResponse
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}

