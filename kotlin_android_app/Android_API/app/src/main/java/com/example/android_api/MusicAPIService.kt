package com.example.android_api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class MusicResponse(
    val protocol: String,
    val code: String,
    var url: String
)

interface MusicAPIService{
    @GET("music/")
    suspend fun getAllMusic(): List<Music>

    @GET("music/{id}")
    suspend fun getMusic(
        @Path("id") musicId: String
    ): Music

    @POST("/music")
    suspend fun addMusic(
        @Body music: Music
    ): Response<MusicResponse>

    @PUT("/music/{id}")
    suspend fun updateMusic(
        @Path("id") musicId: String,
        @Body music: Music
    ): Response<MusicResponse>

    @DELETE("/music/{id}")
    suspend fun deleteMusic(
        @Path("id") musicId: String
    ): Response<MusicResponse>
}
