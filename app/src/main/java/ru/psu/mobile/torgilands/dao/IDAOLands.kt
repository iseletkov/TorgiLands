package ru.psu.mobile.torgilands.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.psu.mobile.torgilands.model.CLand
import java.util.UUID

@Dao
interface IDAOLands {
    @Query("SELECT * FROM lands")
    fun getAll(): Flow<List<CLand>>

    @Query("SELECT * FROM lands WHERE id = :id")
    fun getById(
        id : UUID
    )                                       : Flow<CLand>

    @Insert
    suspend fun insert(land: CLand)

    @Delete
    suspend fun delete(land: CLand)
}