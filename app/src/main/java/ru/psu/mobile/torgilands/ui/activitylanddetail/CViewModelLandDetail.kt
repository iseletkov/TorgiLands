package ru.psu.mobile.torgilands.ui.activitylanddetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.model.CLand
import java.util.UUID

/********************************************************************************************************
 * Модель представления для активности с отображением информации по одному участку.                     *
 *******************************************************************************************************/
//https://developer.android.com/topic/libraries/data-binding/architecture
class CViewModelLandDetail                  : ViewModel() {

    private lateinit var id                 : UUID

    //Хранение информации, отображаемой в полях ввода на форме.
    //Для удобства доступа с формы через DataBinding.
    val header                              = MutableStateFlow("")
    val price                               = MutableStateFlow("")
    val square                              = MutableStateFlow("")
    val type                                = MutableStateFlow("")

    //Факт установки начального значения, полученного из окна со списком.
    private val _initilized                 = MutableStateFlow(false)
    val initilized                          : StateFlow<Boolean>
                                            = _initilized.asStateFlow()

    // Информация в виде объекта класса из предметной области,
    // проверенная, очищенная от ошибок ввода.
    //По-умолчанию подсталяется пустой объект.
    private val _land                       = MutableStateFlow(CLand(UUID.randomUUID()))
    val land                                : StateFlow<CLand>
                                            = _land.asStateFlow()

    //Отображаемое сообщение
    private val _message                    = MutableSharedFlow<Int>(
        replay                              = 1,
        onBufferOverflow                    = BufferOverflow.DROP_OLDEST
    )
    val message                             : SharedFlow<Int>
                                            = _message.asSharedFlow()

    /****************************************************************************************************
     * Метод позволяет задать элемент данных - участок, с которым будет производиться работа на форме.  *
     * TODO брать из БД.                                                                                *
     ***************************************************************************************************/
    fun setItem(
        id                                  : UUID,
        header                              : String,
        price                               : Double,
        square                              : Double,
        type                                : String
    )
    {
        //Запоминаем элемент данных.
        this.id                             = id
        this.header.update { header }
        this.price.update { price.toString() }
        this.square.update { square.toString() }
        this.type.update { type }

        //Также запоминаем факт того, что элемент уже задан,
        //чтобы повторно не затирать введённые данные при поворотах экрана.
        _initilized.update { true }
    }

    /****************************************************************************************************
     * Обработка действия "сохранить".                                                                  *
     * TODO добавить сохранение в БД.                                                                   *
     ***************************************************************************************************/
    fun save()                              : Boolean
    {
        //Проверка корректности текущих данных и сообщение пользователю.
        val price                           = try{
            this.price.value.toDouble()
        }
        catch(e                             : NumberFormatException)
        {
            //Если пользователь ввёл что-то отличное от числа,
            //генерируем факт необходимости вывода сообщения с нужным идентификатором.
            _message.tryEmit(
                R.string.MESSAGE_WRONG_FORMAT_PRICE
            )
            //Также возвращаем факт неуспешности проверки в активность.
            return false
        }
        val square                           = try{
            this.square.value.toDouble()
        }
        catch(e                             : NumberFormatException)
        {
            _message.tryEmit(
                R.string.MESSAGE_WRONG_FORMAT_SQUARE
            )
            return false
        }
        //Обновление элемента данных,
        //который дальше передаётся в предыдущую форму или БД.
        _land.update { previous->
            previous.copy(
                id                          = id,
                header                      = header.value,
                price                       = price,
                square                      = square,
                type                        = type.value
            )
        }
        //TODO Вызвать сохранение в БД.

        return true
    }

}