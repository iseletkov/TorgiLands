package ru.psu.mobile.torgilands.ui.activitylandlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.psu.mobile.torgilands.databinding.LandListItemBinding
import ru.psu.mobile.torgilands.model.CLand

// Инструкция Google по подключению RecyclerView
//https://developer.android.com/develop/ui/views/layout/recyclerview

// Пример использования ViewBinding
//https://stackoverflow.com/questions/60423596/how-to-use-viewbinding-in-a-recyclerview-adapter

//Официальная инструкция по DataBinding
///https://developer.android.com/topic/libraries/data-binding
/*
 *  @param items - список элементов данных для отображения.
 */
class CRecyclerViewAdapterLandList(

    private val items                       : List<CLand>,
    private val clickListener               : (index : Int) -> Unit
)                                           : RecyclerView.Adapter<CRecyclerViewAdapterLandList.ViewHolder>()
{
    /*
     * Класс отвечает за хранение ссылок на элементы интерфейса для отображения одного элемента данных.
     * @param itemBinding - экземпляр сгенерированного класса, позволяющего проще обращаться к элементам интерфейса.
     */
    class ViewHolder(
        private val itemBinding             : LandListItemBinding,
        private val clickListener           : (index : Int) -> Unit
    ) : RecyclerView.ViewHolder(
        //Ссылка на корневой элемент карточки (LinearLayoutCompat)
        itemBinding.root
    ) {
        private var index : Int = -1
        init {
            itemBinding.root.setOnClickListener {
                clickListener(index)
            }
        }
        /*
         * Метод отвечает за вывод на экран одного элемента данных.
         * @param item - элемент данных, который выводится на экран.
         */
        fun bindItem(
            item : CLand,
            index : Int
        )
        {
            itemBinding.land = item
            this.index = index
//            itemBinding.TextViewHeader.text = item.header
//            itemBinding.TextViewType.text = item.type
//            itemBinding.TextViewPrice.text = "${String.format("%.2f", item.price)} руб."
//            itemBinding.TextViewSquare.text = "${String.format("%.2f", item.square)} м2"
        }
    }

    /*
     * Метод вызывается при создании новой карточки в списке.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.land_list_item, parent, false)
        //Считываются элементы графического интерфейса,
        //ссылки записывают в переменную itemBinding
        val itemBinding = LandListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, clickListener)
    }

    //Возвращает полное количество элементов в списке.
    override fun getItemCount(): Int {
        return items.size
    }

    /*
     * Вызывается, когда старая карточка переиспользуется для вывода нового элемента данных.
     * @param holder - ссылка на старую карточку.
     * @param position - порядковый номер нового элемента данных для вывода.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], position)
    }
}