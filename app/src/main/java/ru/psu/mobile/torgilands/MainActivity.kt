package ru.psu.mobile.torgilands

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ru.psu.mobile.torgilands.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    lateinit var textViewOutput : TextView
//    lateinit var editTextValue1 : EditText
//    lateinit var editTextValue2 : EditText

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        textViewOutput = findViewById(R.id.textView2)
//        editTextValue1 = findViewById(R.id.editTextValue1)
//        editTextValue2 = findViewById(R.id.editTextValue2)
        binding.textView2.text = getString(R.string.initial_output)

        binding.buttonPlus.setOnLongClickListener {
            Toast.makeText(
                this,
                "Долгое нажатие!",
                Toast.LENGTH_LONG

            ).show()
            return@setOnLongClickListener true
        }

    }

    fun onButtonPlusClick(view : View)
    {
        val val1 = binding.editTextValue1.text.toString().toDouble()
        val val2 = binding.editTextValue2.text.toString().toDouble()
        binding.textView2.text = ""+(val1+val2)
    }
}