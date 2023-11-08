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
/*
 *  @param items - список элементов данных для отображения.
 */
class CRecyclerViewAdapterLandList(

    private val items                       : List<CLand>
)                                           : RecyclerView.Adapter<CRecyclerViewAdapterLandList.ViewHolder>()
{
    /*
     * Класс отвечает за хранение ссылок на элементы интерфейса для отображения одного элемента данных.
     * @param itemBinding - экземпляр сгенерированного класса, позволяющего проще обращаться к элементам интерфейса.
     */
    class ViewHolder(
        private val itemBinding: LandListItemBinding
    ) : RecyclerView.ViewHolder(
        //Ссылка на корневой элемент карточки (LinearLayoutCompat)
        itemBinding.root
    ) {
        /*
         * Метод отвечает за вывод на экран одного элемента данных.
         * @param item - элемент данных, который выводится на экран.
         */
        fun bindItem(item : CLand)
        {
            itemBinding.land = item
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
//        val binding : LandListItemBinding =  DataBindingUtil.inflate(
//            LayoutInflater.from(parent.context),
//            R.layout.land_list_item,
//            parent, false)
        val itemBinding = LandListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
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
        holder.bindItem(items[position])
    }
}