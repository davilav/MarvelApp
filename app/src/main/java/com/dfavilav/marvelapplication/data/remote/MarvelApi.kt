package com.dfavilav.marvelapplication.data.remote

import com.dfavilav.marvelapplication.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters/{character_id}/")
    suspend fun getAllComicsByCharacter(
        @Path("character_id") characterId: Int,
        @Query("ts") timeStamp: Int = 1000,
        @Query("apikey") apiKey: String = "4839d517cbd95f77daccd79268143e6d",
        @Query("hash") hash: String = "296a339c775ab44827539831d4ca46b6",
    ): ApiResponse
}