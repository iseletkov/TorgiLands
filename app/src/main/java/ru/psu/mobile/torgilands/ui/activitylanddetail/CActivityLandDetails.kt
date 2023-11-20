package ru.psu.mobile.torgilands.ui.activitylanddetail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.databinding.ActivityLandDetailsBinding
import java.util.UUID

/********************************************************************************************************
 * Активность со списком участков на торгах, реализованная в классическом варианте.                     *
 *******************************************************************************************************/
class CActivityLandDetails                  : AppCompatActivity() {
    //Подключение модели представления
    private val viewModel                   : CViewModelLandDetail by viewModels()

    private lateinit var binding            : ActivityLandDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Настройка DataBinding.
        binding                             = ActivityLandDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Проверяем факт обработки переданных из предыдущей активности параметров.
        if (!viewModel.initilized.value) {
            intent.extras?.let {bundle ->
                val id                      = bundle.getString(getString(R.string.PARAM_ID))?.let { tempId ->
                    UUID.fromString(tempId)
                }
                //Если идентификатор не указан,
                //выдаём сообщение
                id?:run{
                    //Можно сообщение прокрутить через модель представления.
                    Toast.makeText(
                        this@CActivityLandDetails,
                        getString(R.string.INTERNAL_ERROR),
                        Toast.LENGTH_LONG
                    )
                        .show()
                    return@let
                }

                //Передаём начальные данные в модель представления.
                viewModel.setItem(
                    id                      = id,
                    header                  = bundle.getString(getString(R.string.PARAM_HEADER), ""),
                    type                    = bundle.getString(getString(R.string.PARAM_TYPE), ""),
                    price                   = bundle.getDouble(getString(R.string.PARAM_PRICE)),
                    square                  = bundle.getDouble(getString(R.string.PARAM_SQUARE))
                )
            }
        }
        //Передаём в DataBinding в качестве переменной для связывания всю модель представления.
        binding.viewModel                   = viewModel

        //Обработка нажатия кнопки "Сохранить".
        binding.buttonSave.setOnClickListener {
            //Проверяем и сохраняем данные.
            if (!viewModel.save())
                //Если данные содержат проблемы,
                //дальнейшие действия не производим.
                return@setOnClickListener

            //Если проверка пройдена успешно, данные сохранены,
            //тогда закрываем текущую активность и возвращаем введённые данные в предыдущую.
            //TODO В случае БД Room+Flow эта передача не требуется.
            val data                        = Intent()
            data.putExtra(getString(R.string.PARAM_ID),viewModel.land.value.id.toString())
            data.putExtra(getString(R.string.PARAM_HEADER), viewModel.land.value.header)
            data.putExtra(getString(R.string.PARAM_PRICE), viewModel.land.value.price)
            data.putExtra(getString(R.string.PARAM_SQUARE), viewModel.land.value.square)
            data.putExtra(getString(R.string.PARAM_TYPE), viewModel.land.value.type)
            setResult(RESULT_OK,data)
            finish()
        }
        //Обработка нажатия кнопки "Отмена"
        binding.buttonCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        //Подписываемся на возможные сообщения из ViewModel.
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.message.collect {stringId->
                    if (stringId<0)
                        return@collect
                    //При получении информации о сообщении,
                    //выводим его на экран.
                    Toast.makeText(
                        this@CActivityLandDetails,
                        getString(stringId),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }
    }
}