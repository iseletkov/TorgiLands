package ru.psu.mobile.torgilands.ui.activitylandlist

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.model.CLand
import android.view.LayoutInflater

//https://developer.android.com/develop/ui/views/layout/recyclerview
class CRecyclerViewAdapterLandList(
    private val items                       : List<CLand>
)                                           : RecyclerView.Adapter<CRecyclerViewAdapterLandList.ViewHolder>()
{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val textViewHeader                  : TextView
                                            = view.findViewById(R.id.TextViewHeader)
        private val textViewType                    : TextView
                = view.findViewById(R.id.TextViewType)
        private val textViewPrice                  : TextView
                = view.findViewById(R.id.TextViewPrice)
        private val textViewSquare                 : TextView
                = view.findViewById(R.id.TextViewSquare)

        fun bindItem(item : CLand)
        {
            textViewHeader.text = item.header
            textViewType.text = item.type
            textViewPrice.text = "${String.format("%.2f", item.price)} руб."
            textViewSquare.text = "${String.format("%.2f", item.square)} м2"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.land_list_item, parent, false)

        return ViewHolder(view)
    }

    //Возвращает полное количество элементов в списке.
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}