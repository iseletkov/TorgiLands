package ru.psu.mobile.torgilands.ui.activitylanddetail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.databinding.ActivityLandDetailsBinding
import java.util.UUID

class CActivityLandDetails : AppCompatActivity() {

    val viewModel: CViewModelLandDetail by viewModels()

    private lateinit var binding : ActivityLandDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding             = ActivityLandDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        bundle?.let{
            viewModel.save(
                id = bundle.getString(getString(R.string.PARAM_ID))?.let { tempId ->
                    UUID.fromString(tempId)
                },
                header = bundle.getString("PARAM_HEADER", ""),
                type = bundle.getString("PARAM_TYPE", ""),
                price = bundle.getDouble("PARAM_PRICE"),
                square = bundle.getDouble("PARAM_SQUARE")
            )
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.land.collect {
                    binding.textFieldHeader.editText?.setText(it.header)
                    binding.textFieldPrice.editText?.setText(it.price.toString())
                    binding.textFieldSquare.editText?.setText(it.square.toString())
                    binding.textFieldType.editText?.setText(it.type)
                }
            }
        }

        binding.buttonSave.setOnClickListener {


            val data = Intent()
            data.putExtra(getString(R.string.PARAM_ID),id.toString())
            data.putExtra("PARAM_HEADER", binding.textFieldHeader.editText?.text.toString())
            data.putExtra("PARAM_TYPE", binding.textFieldType.editText?.text.toString())
            data.putExtra("PARAM_PRICE", binding.textFieldPrice.editText?.text.toString().toDouble())
            data.putExtra("PARAM_SQUARE", binding.textFieldSquare.editText?.text.toString().toDouble())
            setResult(RESULT_OK,data)
            finish()

        }
        binding.buttonCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

    }
}