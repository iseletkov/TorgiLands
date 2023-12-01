package ru.psu.mobile.torgilands.ui.activitylandlist

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.psu.mobile.torgilands.CApplication
import ru.psu.mobile.torgilands.databinding.LandListItemBinding
import ru.psu.mobile.torgilands.model.CLand


// Инструкция Google по подключению RecyclerView
//https://developer.android.com/develop/ui/views/layout/recyclerview

// Пример использования ViewBinding
//https://stackoverflow.com/questions/60423596/how-to-use-viewbinding-in-a-recyclerview-adapter

//Официальная инструкция по DataBinding
///https://developer.android.com/topic/libraries/data-binding

//Автоматическая проверка изменений в списке.
//https://davy.ai/update-recyclerview-from-stateflow-doesnt-work/
/*
 *  @param items - список элементов данных для отображения.
 */
class CAdapterLandList
//Конструктор данного класса.
(
    private val clickListener               : (land : CLand?) -> Unit,
    private val deleteListener              : (land : CLand?) -> Unit
)                                           : ListAdapter<CLand, CAdapterLandList.ViewHolder>
//Конструктор родителя.
    (
        CDiffCallback()
    )
{
    /*
     * Класс отвечает за хранение ссылок на элементы интерфейса для отображения одного элемента данных.
     * @param itemBinding - экземпляр сгенерированного класса, позволяющего проще обращаться к элементам интерфейса.
     */
    class ViewHolder(
        private val itemBinding             : LandListItemBinding,
        private val clickListener           : (land : CLand?) -> Unit,
        private val deleteListener          : (land : CLand?) -> Unit
    ) : RecyclerView.ViewHolder(
        //Ссылка на корневой элемент карточки (LinearLayoutCompat)
        itemBinding.root
    ) {
        init {
            itemBinding.root.setOnClickListener {
                clickListener(itemBinding.land)
            }
            itemBinding.buttonDelete.setOnClickListener {
                deleteListener(itemBinding.land)
            }
        }
        /*
         * Метод отвечает за вывод на экран одного элемента данных.
         * @param item - элемент данных, который выводится на экран.
         */
        fun bindItem(
            item : CLand
        )
        {
            //Нерабочий вариант с прямым доступом к min.io
//            itemBinding.land = item
//            CApplication.minioClient.getObject(
//                GetObjectArgs.builder()
//                    .bucket("torgilands")
//                    .`object`(""+item.id+".png")
//                    .build()
//            ).use { stream ->
//                val bitmap = BitmapFactory.decodeStream(stream)
//                itemBinding.ImageViewPreview.setImageBitmap(bitmap)
//            }
            //Рабочий вариант со статической ссылкой.
            kotlinx.coroutines.MainScope().launch {
                val responseBody = CApplication
                    .apiService
                    .downloadFileWithDynamicUrlSync("https://cdn.nekosapi.com/images/original/517884e1-1960-41a6-93ab-09bcb0ab5f3f.webp")
                responseBody.byteStream()
                    .use{stream ->
                        val bitmap = BitmapFactory.decodeStream(stream)
                        itemBinding.ImageViewPreview.setImageBitmap(bitmap)
                    }

            }

        }
    }

    /*
     * Метод вызывается при создании новой карточки в списке.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Считываются элементы графического интерфейса,
        //ссылки записывают в переменную itemBinding
        val itemBinding = LandListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(
            itemBinding,
            clickListener,
            deleteListener
        )
    }

    /*
     * Вызывается, когда старая карточка переиспользуется для вывода нового элемента данных.
     * @param holder - ссылка на старую карточку.
     * @param position - порядковый номер нового элемента данных для вывода.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    private class CDiffCallback             : DiffUtil.ItemCallback<CLand>() {
        override fun areItemsTheSame(
            oldItem                         : CLand,
            newItem                         : CLand
        )                                   : Boolean
        {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem                         : CLand,
            newItem                         : CLand
        )                                   : Boolean
        {
            return oldItem == newItem
        }
    }
}