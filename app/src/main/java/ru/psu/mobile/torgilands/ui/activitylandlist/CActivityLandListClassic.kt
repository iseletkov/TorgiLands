package ru.psu.mobile.torgilands.ui.activitylandlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.databinding.ActivityLandListClassicBinding
import ru.psu.mobile.torgilands.ui.activitylanddetail.CActivityLandDetails
import java.util.UUID

/********************************************************************************************************
 * Активность со списком участков на торгах, реализованная в классическом варианте.                     *
 *******************************************************************************************************/
class CActivityLandListClassic              : AppCompatActivity() {
    //Подключение модели представления
    private val viewModel                   : CViewModelLandList by viewModels()
    private lateinit var binding            : ActivityLandListClassicBinding
    private lateinit var resultLauncher     : ActivityResultLauncher<Intent>
    private lateinit var listAdapter        : CAdapterLandList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Настройка ViewBinding
        binding                             = ActivityLandListClassicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Создание и настройка экземпляра класса адаптера списка.
        listAdapter                         = CAdapterLandList(
            //Обработчик клика на элемент списка.
            clickListener                   = {land ->
                land?:return@CAdapterLandList
                val intent                  = Intent(
                    this,
                    CActivityLandDetails::class.java
                )
                intent.putExtra(getString(R.string.PARAM_ID), land.id.toString())
//                intent.putExtra(getString(R.string.PARAM_HEADER), land.header)
//                intent.putExtra(getString(R.string.PARAM_TYPE), land.type)
//                intent.putExtra(getString(R.string.PARAM_PRICE), land.price)
//                intent.putExtra(getString(R.string.PARAM_SQUARE), land.square)
                resultLauncher.launch(intent)
            },
            //Обработчик удаления элемента.
            deleteListener                  = {land ->
                viewModel.deleteItem(land)
            }
        )

        binding.RecyclerViewLandList.adapter= listAdapter

        val mLayoutManager                  = LinearLayoutManager(this)
        binding.RecyclerViewLandList.layoutManager = mLayoutManager

        resultLauncher                      = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->

//            if (result.resultCode != Activity.RESULT_OK)
//                return@registerForActivityResult
//
//
//            // There are no request codes
//            val bundle                      = result.data?.extras
//            bundle?.let{
//                val id                      = bundle.getString(getString(R.string.PARAM_ID))?.let { tempId ->
//                    UUID.fromString(tempId)
//                }
//                //Если идентификатор не указан,
//                //ничего не делаем.
//                id?:return@let
//
//                val header                  = bundle.getString(getString(R.string.PARAM_HEADER), "")
//                val type                    = bundle.getString(getString(R.string.PARAM_TYPE), "")
//                val price                   = bundle.getDouble(getString(R.string.PARAM_PRICE))
//                val square                  = bundle.getDouble(getString(R.string.PARAM_SQUARE))
//
//                viewModel.editItem(
//                    id,
//                    header,
//                    price,
//                    square,
//                    type
//                )
//            }
        }
        binding.fab.setOnClickListener {
            val intent                  = Intent(
                this,
                CActivityLandDetails::class.java
            )
            intent.putExtra(getString(R.string.PARAM_ID), UUID.randomUUID().toString())
            resultLauncher.launch(intent)

        }

        //Подписываемся на изменение списка элементов.
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect { items->
                    //При получении новой версии списка отправляем его в адаптер.
                    //Адаптер сам решает, какие элементы изменились,
                    //и информирует список по нужным элементам.
                    listAdapter.submitList(items)
                }
            }
        }
    }
}