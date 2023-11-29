package ru.psu.mobile.torgilands.repositories

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.psu.mobile.torgilands.CApplication
import ru.psu.mobile.torgilands.db.CDatabase
import ru.psu.mobile.torgilands.model.CLand
import java.util.UUID

class CRepositoryLands(
    context                                 : Context
)
{
    private val db                          = CDatabase.getDatabase(context)
    private val daoLands                    = db.daoLands()

    private val serviceAPI                  = CApplication.apiService

    fun getAll()                            : Flow<List<CLand>>
    {

        //Есть ли данные локально?
        //Спросить у сервера
        //Получить ответ от сервера
        //Сохранить новые данные локально
        //Взять из локального источника актуальные данные
        //return daoLands.getByHeaderAndPrice("123", 123.0)
        //return daoLands
        // .getAll()
        // .flowOn(Dispatchers.IO)
        return flow{
            emit (
                serviceAPI
                    .getLands()
            )
        }
            .flowOn(Dispatchers.IO)
    }

    fun getById(
        id : UUID
    ) : Flow<CLand?>
    {
        return daoLands.getById(id)
    }

    suspend fun insert(
        land: CLand
    )
    {
        daoLands.insert(land)
        serviceAPI.postLand(land)
    }

    suspend fun delete(
        land: CLand
    )
    {
        daoLands.delete(land)
    }
}