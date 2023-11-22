package ru.psu.mobile.torgilands.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ru.psu.mobile.torgilands.db.CDatabase
import ru.psu.mobile.torgilands.model.CLand

class CRepositoryLands(
    context                                 : Context
)
{
    private val db                          = CDatabase.getDatabase(context)
    private val daoLands                    = db.daoLands()

    fun getAll()                            : Flow<List<CLand>>
    {
        //Есть ли данные локально?
        //Спросить у сервера
        //Получить ответ от сервера
        //Сохранить новые данные локально
        //Взять из локального источника актуальные данные
        //return daoLands.getByHeaderAndPrice("123", 123.0)
        return daoLands.getAll()
    }

    suspend fun insert(
        land: CLand
    )
    {
        daoLands.insert(land)
    }

    suspend fun delete(
        land: CLand
    )
    {
        daoLands.delete(land)
    }
}