package ru.psu.mobile.torgilands.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.psu.mobile.torgilands.model.CLand
import java.util.UUID

@Dao
interface IDAOLands {
    @Query("SELECT * FROM lands ORDER BY header")
    fun getAll()                            : Flow<List<CLand>>

    @Query("SELECT * FROM lands WHERE id = :id")
    fun getById(
        id                                  : UUID
    )                                       : Flow<CLand?>

    @Query("""
        SELECT * FROM lands 
        WHERE 
            header LIKE :header  
            AND
            price = :price
    """)
    fun getByHeaderAndPrice(
        header : String,
        price : Double
    )                                       : Flow<List<CLand>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(land: CLand) //async

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(lands: List<CLand>) //async

    @Update
    suspend fun update(land: CLand)

    @Delete
    suspend fun delete(land: CLand)
}