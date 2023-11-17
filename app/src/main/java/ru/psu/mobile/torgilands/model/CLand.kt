package ru.psu.mobile.torgilands.model

import java.util.UUID

data class CLand (
    var id                                  : UUID?
                                            = null,
    var header                              : String
                                            = "",
    var price                               : Double
                                            = 0.0,
    var square                              : Double
                                            = 0.0,
    var type                                : String
                                            = ""
)