package ru.psu.mobile.torgilands.ui.activitylanddetail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.databinding.ActivityLandDetailsBinding
import java.util.UUID

class CActivityLandDetails : AppCompatActivity() {
    private var id : UUID?
                    = null

    private var header = ""
    private var price = 0.0
    private var square = 0.0
    private var type = ""

    private lateinit var binding : ActivityLandDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding             = ActivityLandDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = intent.extras
        bundle?.let{
            id = bundle.getString(getString(R.string.PARAM_ID))?.let { tempId ->
                UUID.fromString(tempId)
            }
            header = bundle.getString("PARAM_HEADER", "")
            type = bundle.getString("PARAM_TYPE", "")
            price = bundle.getDouble("PARAM_PRICE")
            square = bundle.getDouble("PARAM_SQUARE")
        }
        id = id?: UUID.randomUUID()

        binding.textFieldHeader.editText?.setText(header)
        binding.textFieldPrice.editText?.setText(price.toString())
        binding.textFieldSquare.editText?.setText(square.toString())
        binding.textFieldType.editText?.setText(type)

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