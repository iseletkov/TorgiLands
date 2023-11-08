package ru.psu.mobile.torgilands.ui.activitylandlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.databinding.ActivityLandListClassicBinding
import ru.psu.mobile.torgilands.model.CLand
import ru.psu.mobile.torgilands.ui.activitylanddetail.CActivityLandDetails
import java.util.UUID


class CActivityLandListClassic : AppCompatActivity() {
    private lateinit var binding : ActivityLandListClassicBinding
    lateinit var resultLauncher             : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding             = ActivityLandListClassicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataset = mutableListOf(
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
        val customAdapter                   = CRecyclerViewAdapterLandList(dataset)

        binding.RecyclerViewLandList.adapter = customAdapter

        val mLayoutManager                  = LinearLayoutManager(this)
        binding.RecyclerViewLandList.layoutManager = mLayoutManager

        resultLauncher                      = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode != Activity.RESULT_OK)
                return@registerForActivityResult


            // There are no request codes
            val bundle = result.data?.extras
            bundle?.let{
                val id = bundle.getString(getString(R.string.PARAM_ID))?.let { tempId ->
                    UUID.fromString(tempId)
                }

                id?: return@registerForActivityResult

                val header = bundle.getString("PARAM_HEADER", "")
                val type = bundle.getString("PARAM_TYPE", "")
                val price = bundle.getDouble("PARAM_PRICE")
                val square = bundle.getDouble("PARAM_SQUARE")
                var index = -1
                dataset
                    .forEachIndexed { ind, land ->
                        if (land.id==id)
                        {
                            land.header = header
                            land.type = type
                            land.price = price
                            land.square = square
                            index = ind
                        }
                    }
                if (index<0)
                {
                    dataset.add(
                        CLand(
                            id ,
                            header,
                            price,
                            square,
                            type
                        )
                    )
                    index = dataset.size-1
                }
                customAdapter.notifyItemChanged(index)
            }


        }
        binding.fab.setOnClickListener {
            val intent                  = Intent(
                this,
                CActivityLandDetails::class.java
            )
            resultLauncher.launch(intent)
        }
    }
}