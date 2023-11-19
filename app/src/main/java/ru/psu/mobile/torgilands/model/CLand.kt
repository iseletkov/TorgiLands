package ru.psu.mobile.torgilands.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName                               = "lands"
)
data class CLand (
    //SQLite не даёт использовать обнуляемые типы данных в качестве первичных ключей.
    @PrimaryKey
    var id                                  : UUID,

    @ColumnInfo(
        name                                = "header"
    )
    var header                              : String
                                            = "",
    @ColumnInfo
    var price                               : Double
                                            = 0.0,
    @ColumnInfo
    var square                              : Double
                                            = 0.0,
    @ColumnInfo
    var type                                : String
                                            = ""
)