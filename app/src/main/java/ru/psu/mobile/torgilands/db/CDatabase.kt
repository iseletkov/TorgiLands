package ru.psu.mobile.torgilands.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.psu.mobile.torgilands.dao.IDAOLands
import ru.psu.mobile.torgilands.model.CLand

@Database(
    entities = [CLand::class],
    version = 1
)
abstract class CDatabase : RoomDatabase() {
    abstract fun daoLands(): IDAOLands

    companion object {
        @Volatile
        private var INSTANCE: CDatabase? = null
        fun getDatabase(context: Context): CDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room
                            .databaseBuilder(
                                context,
                                CDatabase::class.java,
                                "database.db")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

}