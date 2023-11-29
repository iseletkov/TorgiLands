package ru.psu.mobile.torgilands.model

import com.squareup.moshi.Json

data class CDummyResponseEmployees<DataType> (
    @Json(name = "status")
    val status: String,
    @Json(name = "message")
    val message: String,
    @Json(name = "data")
    val data: DataType
)