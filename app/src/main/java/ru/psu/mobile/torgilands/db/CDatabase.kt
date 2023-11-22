package ru.psu.mobile.torgilands.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.psu.mobile.torgilands.dao.IDAOLands
import ru.psu.mobile.torgilands.model.CLand

// https://developer.android.com/training/data-storage/room
// https://www.geeksforgeeks.org/how-to-use-singleton-pattern-for-room-database-in-android/
// https://sqlitebrowser.org/

@Database(
    entities = [CLand::class],
    version = 1
)
//@TypeConverters(CConverterUUID::class)
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