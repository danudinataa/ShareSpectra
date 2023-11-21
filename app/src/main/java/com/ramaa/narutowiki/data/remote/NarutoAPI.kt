package com.ramaa.narutowiki.data.remote

import com.ramaa.narutowiki.data.remote.response.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NarutoAPI {

    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int,

    ): CharactersResponse

    @GET("characters")
    suspend fun searchCharacter(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
    ): CharactersResponse
}