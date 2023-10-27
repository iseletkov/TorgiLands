package ru.psu.mobile.torgilands

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
        val val1 = binding.editTextValue1.editText?.text.toString().toDouble()
        val val2 = binding.editTextValue2.editText?.text.toString().toDouble()
        binding.textView2.text = ""+(val1+val2)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.activity_calculator_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.settings -> {
                Toast.makeText(
                    this,
                    "Нажата кнопка настроек",
                    Toast.LENGTH_LONG
                ).show()
                true
            }
            R.id.help -> {
                Toast.makeText(
                    this,
                    "Нажата кнопка справки",
                    Toast.LENGTH_LONG
                ).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}