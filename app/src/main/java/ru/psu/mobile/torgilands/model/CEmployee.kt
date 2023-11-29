package ru.psu.mobile.torgilands.model

import com.squareup.moshi.Json

data class CEmployee(
    @Json(name = "id")
    val id : Int,
    @Json(name = "employee_name")
    val employee_name : String
)