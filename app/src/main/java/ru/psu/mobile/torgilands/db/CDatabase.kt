package ru.psu.mobile.torgilands.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.psu.mobile.torgilands.dao.IDAOLands
import ru.psu.mobile.torgilands.model.CLand

@Database(
    entities = [CLand::class],
    version = 1
)
abstract class CDatabase : RoomDatabase() {
    abstract fun daoLands(): IDAOLands

}