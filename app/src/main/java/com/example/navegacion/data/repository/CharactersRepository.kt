package com.example.navegacion.data.repository

import com.example.navegacion.data.client.ApiClient

class CharacterRepository {

    suspend fun getCharacters() =
        ApiClient.api.getCharacters()
}