package ru.psu.mobile.torgilands.ui.activitylandlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.psu.mobile.torgilands.model.CLand
import java.util.UUID

/********************************************************************************************************
 * Модель представления для активности с отображением списка участков на торгах.                        *
 *******************************************************************************************************/
class CViewModelLandList : ViewModel()
{
    //Собственно хранилище данных, без возможности подписки.
    private val itemsRepository                  = mutableListOf(
        CLand(
            UUID.randomUUID(),
            "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
            123456.00,
            1200.0,
            "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
        ),
        CLand(
            UUID.randomUUID(),
            "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
            123456.00,
            1200.0,
            "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
        ),
        CLand(
            UUID.randomUUID(),
            "Предмет аукциона – земельный участок, государственная собственность на который не разграничена, с кадастровым номером 12:04:0870113:991, категория земель – земли населенных пунктов, разрешенное использование – обслуживание автотранспорта, площадью 1345 кв. м., расположенный по адресу: Российская Федерация, Республика Марий Эл, Медведевский муниципальный район, пгт. Медведево, в границах, соответствующих описанию в сведениях государственного кадастра недвижимости (далее – земельный участок).",
            123456.00,
            1200.0,
            "Иной вид (установлен до дня утверждения в соответствии с ЗК РФ классификатора)"
        ),
        CLand(
            UUID.randomUUID(),
            "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
            123456.00,
            1200.0,
            "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
        ),
        CLand(
            UUID.randomUUID(),
            "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
            123456.00,
            1200.0,
            "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
        )
    )
    //Редактируемый список элементов с возможностью подписки на изменения
    //для внутреннего использования.
    private var itemsFlow                   = MutableSharedFlow<List<CLand>>(
        replay                              = 1,
        onBufferOverflow                    = BufferOverflow.DROP_OLDEST
    )
    //Нередактируемый список элементов с возможностью подписки на изменения
    //Для предоставления в активность.
    val items                               : SharedFlow<List<CLand>>
                                            = itemsFlow.asSharedFlow()
    init {
        itemsFlow.tryEmit(
            itemsRepository.toList()
        )
    }
    fun addItem(
        item                                : CLand
    )
    {
        itemsRepository.add(item)
        //TODO Здесь должно быть сохранение в БД.
        itemsFlow.tryEmit(itemsRepository.toList())
//        itemsFlow.update { _ ->
//            itemsRepository.toList()
//        }
    }
    fun deleteItem(
        item                                : CLand?
    )
    {
        item?:return

        //Проводим поиск по идентификатору,
        //потому что просто удаление проверяет полное совпадение полей.
        val index                           = itemsRepository.indexOfFirst { tempItem -> tempItem.id == item.id}
        if (index<0)
            return

        itemsRepository.removeAt(index)
        itemsFlow.tryEmit(itemsRepository.toList())
//        itemsFlow.update {_ ->
//            itemsRepository.toList()
//        }
    }
    fun editItem(
        id                                  : UUID,
        header                              : String,
        price                               : Double,
        square                              : Double,
        type                                : String
    )
    {
        val index                           = itemsRepository.indexOfFirst {item ->item.id==id }
        if (index<0)
            return
        val item                            = CLand(
            id                              = id,
            header                          = header,
            price                           = price,
            square                          = square,
            type                            = type
        )
        itemsRepository.removeAt(index)
        itemsRepository.add(index, item)
        itemsFlow.tryEmit(itemsRepository.toList())
//        itemsFlow.update {_ ->
//            itemsRepository.toList()
//        }
    }
}