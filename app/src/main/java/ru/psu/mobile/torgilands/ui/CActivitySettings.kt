package ru.psu.mobile.torgilands.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.psu.mobile.torgilands.ui.ui.theme.TorgiLandsTheme


class CActivitySettings : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorgiLandsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        val bundle = intent.extras
        bundle?.let{
            Toast.makeText(
                this,
                bundle.getString("MY_PARAM1", "Параметр не найден!"),
                Toast.LENGTH_LONG
            ).show()
        }?:run{
            Toast.makeText(
                this,
                "Параметры недоступны!",
                Toast.LENGTH_LONG
            ).show()
        }

        onBackPressedDispatcher.addCallback(this /* lifecycle owner */)
        {
            // Back is pressed... Finishing the activity
            val data = Intent()
            data.putExtra("MY_PARAM_2","streetd;ad ada lwdname");
            setResult(RESULT_OK,data);
            finish()
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TorgiLandsTheme {
        Greeting("Android")
    }
}