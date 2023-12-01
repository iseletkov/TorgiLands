package ru.psu.mobile.torgilands.repositories

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
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

        kotlinx.coroutines.MainScope().launch {
            val lands = serviceAPI
                .getLands()
            daoLands.insertAll(lands)
        }

        //В итоге данные берём из локальной СУБД.
        return daoLands
            .getAll()
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
        //Сохранение в локальную БД.
        daoLands.insert(land)
        //Отправка данных на сервер.
        serviceAPI.postLand(land)
    }

    suspend fun delete(
        land: CLand
    )
    {
        //Удаление из локальной СУБД
        daoLands.delete(land)
        //Удаление с сервера
        serviceAPI.deleteLand(land.id)
    }
}