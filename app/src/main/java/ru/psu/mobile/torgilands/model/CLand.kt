package ru.psu.mobile.torgilands.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.UUID

@Entity(
    tableName                               = "lands"
)
data class CLand (
    //SQLite не даёт использовать обнуляемые типы данных в качестве первичных ключей.
    @PrimaryKey
    @Json(name = "id")
    var id                                  : UUID,

    @ColumnInfo(
        name                                = "header"
    )
    var header                              : String
                                            = "",
    @ColumnInfo
    @Json
    var price                               : Double
                                            = 0.0,
    @ColumnInfo
    @Json
    var square                              : Double
                                            = 0.0,
    @ColumnInfo
    @Json
    var type                                : String
                                            = ""
)