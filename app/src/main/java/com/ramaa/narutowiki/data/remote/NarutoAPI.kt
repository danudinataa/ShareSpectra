package com.ramaa.narutowiki.data.remote

import com.ramaa.narutowiki.data.remote.response.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NarutoAPI {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,

    ): CharactersResponse

    @GET("character/search")
    suspend fun searchCharacter(
        @Query("name") searchQuery: String,
        @Query("page") page: Int,
    ): CharactersResponse
}