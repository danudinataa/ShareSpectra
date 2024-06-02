package com.ramaa.pmobile_uas.data.remote

import com.ramaa.pmobile_uas.data.remote.response.CharactersResponse
import com.ramaa.pmobile_uas.data.remote.response.ResultsCompanies
import com.ramaa.pmobile_uas.data.remote.response.StockResponse
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import com.ramaa.pmobile_uas.util.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NarutoAPI {

    @GET("trending")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Header("X-API-KEY") apiKey: String = Constants.API_KEY
    ): StockResponse

    @GET("{symbol}/profile")
    suspend fun searchCharacter(
        @Path("symbol") symbol: String,
        @Header("X-API-KEY") apiKey: String = Constants.API_KEY
    ): ResultsCompanies
}