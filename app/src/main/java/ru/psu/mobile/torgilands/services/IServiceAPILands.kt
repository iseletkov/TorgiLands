package ru.psu.mobile.torgilands.services

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

import ru.psu.mobile.torgilands.model.CLand

interface IServiceAPILands {
    @GET("lands")
    suspend fun getLands() : List<CLand>
    @GET("lands/")
    suspend fun getLandById(
        @Query("id") id : Int
    ): CLand?
    @POST("lands/")
    suspend fun postLand(
        @Body land : CLand
    )
}