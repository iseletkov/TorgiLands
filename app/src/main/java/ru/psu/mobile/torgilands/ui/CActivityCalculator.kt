package ru.psu.mobile.torgilands.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.databinding.ActivityCalculatorBinding

class CActivityCalculator : AppCompatActivity() {

    private lateinit var binding : ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding             = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView2.text = getString(R.string.initial_output)
        binding.buttonPlus.setOnClickListener {
            val val1 = binding.editTextValue1.editText?.text.toString().toDouble()
            val val2 = binding.editTextValue2.editText?.text.toString().toDouble()
            binding.textView2.text = "${val1+val2}"
        }
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