package ru.psu.mobile.torgilands.services

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.UUID
//https://stackoverflow.com/questions/71619766/how-to-use-the-data-type-java-util-uuid-in-moshi
class CAdapterUUID {
    @FromJson
    fun fromJson(uuid: String): UUID = UUID.fromString(uuid)

    @ToJson
    fun toJson(value: UUID): String = value.toString()
}