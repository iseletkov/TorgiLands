package ru.psu.mobile.torgilands.ui.activitylanddetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.psu.mobile.torgilands.model.CLand
import java.util.UUID

class CViewModelLandDetail : ViewModel()
{
    private val _land = MutableStateFlow(CLand())
    val land: StateFlow<CLand> = _land.asStateFlow()

    fun save(
        id : UUID?,
        header : String,
        price : Double,
        square : Double,
        type : String
    )
    {
        _land.update {
            it.copy(
                id = id,
                header = header,
                price = price,
                square = square,
                type = type
            )
        }
    }
}