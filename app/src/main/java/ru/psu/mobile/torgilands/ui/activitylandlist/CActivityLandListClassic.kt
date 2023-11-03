package ru.psu.mobile.torgilands.ui.activitylandlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.model.CLand


class CActivityLandListClassic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_land_list_classic)

        val dataset = listOf(
            CLand(
                "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
                123456.00,
                1200.0,
                "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
            ),
            CLand(
                "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
                123456.00,
                1200.0,
                "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
            ),
            CLand(
                    "Предмет аукциона – земельный участок, государственная собственность на который не разграничена, с кадастровым номером 12:04:0870113:991, категория земель – земли населенных пунктов, разрешенное использование – обслуживание автотранспорта, площадью 1345 кв. м., расположенный по адресу: Российская Федерация, Республика Марий Эл, Медведевский муниципальный район, пгт. Медведево, в границах, соответствующих описанию в сведениях государственного кадастра недвижимости (далее – земельный участок).",
            123456.00,
            1200.0,
            "Иной вид (установлен до дня утверждения в соответствии с ЗК РФ классификатора)"
            ),
            CLand(
                "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
                123456.00,
                1200.0,
                "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
            ),
            CLand(
            "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
            123456.00,
            1200.0,
            "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
            )
        )
        val customAdapter = CRecyclerViewAdapterLandList(dataset)

        val recyclerView: RecyclerView = findViewById(R.id.RecyclerViewLandList)
        recyclerView.adapter = customAdapter

        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager
    }
}