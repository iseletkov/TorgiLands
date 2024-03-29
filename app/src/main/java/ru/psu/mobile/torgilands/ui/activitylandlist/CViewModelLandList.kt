package ru.psu.mobile.torgilands.ui.activitylandlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.psu.mobile.torgilands.model.CLand
import ru.psu.mobile.torgilands.repositories.CRepositoryLands
import java.util.UUID

/********************************************************************************************************
 * Модель представления для активности с отображением списка участков на торгах.                        *
 *******************************************************************************************************/
class CViewModelLandList(
    application: Application
) : AndroidViewModel(application)
{
    private val repositoryLands                 = CRepositoryLands(application)

    //Редактируемый список элементов с возможностью подписки на изменения
    //для внутреннего использования.
    private var itemsFlow                   = MutableStateFlow<List<CLand>>(
        emptyList()
    )
    //Нередактируемый список элементов с возможностью подписки на изменения
    //Для предоставления в активность.
    val items                               : StateFlow<List<CLand>>
                                            = itemsFlow.asStateFlow()
    init {
        viewModelScope.launch {
//            repositoryLands.insert(
//                CLand(
//                    UUID.randomUUID(),
//                    "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
//                    123456.00,
//                    1200.0,
//                    "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
//                )
//            )


            repositoryLands
                .getAll()
                .collect {newItems->
                itemsFlow.update { newItems }
            }
        }
    }

    fun deleteItem(
        item                                : CLand?
    )
    {
        item?:return

        kotlinx.coroutines.MainScope().launch {
            repositoryLands.delete(item)
        }

//        //Проводим поиск по идентификатору,
//        //потому что просто удаление проверяет полное совпадение полей.
//        val index                           = itemsRepository.indexOfFirst { tempItem -> tempItem.id == item.id}
//        if (index<0)
//            return
//
//        itemsRepository.removeAt(index)
//        itemsFlow.tryEmit(itemsRepository.toList())
    }
    fun editItem(
        id                                  : UUID,
        header                              : String,
        price                               : Double,
        square                              : Double,
        type                                : String
    )
    {
//        val item                            = CLand(
//            id                              = id,
//            header                          = header,
//            price                           = price,
//            square                          = square,
//            type                            = type
//        )
//        val index                           = itemsRepository.indexOfFirst {tempItem ->tempItem.id==id }
//        if (index<0)
//        {
//            itemsRepository.add(item)
//        }
//        else {
//            itemsRepository.removeAt(index)
//            itemsRepository.add(index, item)
//        }
//        itemsFlow.tryEmit(itemsRepository.toList())
    }
}