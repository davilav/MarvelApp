package com.dfavilav.marvelapplication.data.remote

import com.dfavilav.marvelapplication.domain.model.ApiResponse
import com.dfavilav.marvelapplication.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters/{character_id}/")
    suspend fun getAllComicsByCharacter(
        @Path("character_id") characterId: Int,
        @Query("limit") limit: Int = Constants.LIMIT,
        @Query("offset") offset: Int = 0,
        @Query("ts") timeStamp: Int = Constants.TIMESTAMP,
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("hash") hash: String = Constants.HASH,
    ): ApiResponse
}