package ru.psu.mobile.torgilands.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.psu.mobile.torgilands.model.CDummyResponseEmployees
import ru.psu.mobile.torgilands.model.CEmployee

interface IAPIDummy {
    @GET("employees")
    suspend fun getEmployees()              : CDummyResponseEmployees<List<CEmployee>>

    @GET("employee/{id}")
    suspend fun getEmployeeById(
        @Path("id") id : Int
    ): CDummyResponseEmployees<CEmployee>

    @POST("create")
    suspend fun postEmployee(
        @Body employee : CEmployee
    )
}