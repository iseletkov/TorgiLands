package ru.psu.mobile.torgilands.services

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url
import ru.psu.mobile.torgilands.model.CLand
import java.util.UUID


interface IServiceAPILands {
    @GET("lands")
    suspend fun getLands() : List<CLand>
    @GET("lands")
    suspend fun getLandById(
        @Query("id") id : UUID
    ): CLand?
    @POST("lands")
    suspend fun postLand(
        @Body land : CLand
    )

    @DELETE("lands")
    suspend fun deleteLand(
        @Query("id") id : UUID
    )

    @GET
    suspend fun downloadFileWithDynamicUrlSync(@Url fileUrl: String): ResponseBody
}