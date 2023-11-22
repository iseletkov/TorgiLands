package ru.psu.mobile.torgilands.repositories

import android.app.Application
import kotlinx.coroutines.flow.Flow
import ru.psu.mobile.torgilands.db.CDatabase
import ru.psu.mobile.torgilands.model.CLand
import java.util.UUID

class CRepositoryLands(
    application                             : Application
) {
    private val db                          = CDatabase.getDatabase(application)
    private val daoLands                    = db.daoLands()

    fun getAll() : Flow<List<CLand>>
    {
        return daoLands.getAll()
    }

    fun getById(
        id : UUID
    ): Flow<CLand>
    {
        return daoLands.getById(id)
    }

    suspend fun insert(land: CLand)
    {
        daoLands.insert(land)
    }

    suspend fun delete(land: CLand)
    {
        daoLands.delete(land)
    }

}