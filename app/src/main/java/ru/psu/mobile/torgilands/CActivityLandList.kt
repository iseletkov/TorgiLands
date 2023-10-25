package ru.psu.mobile.torgilands

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.psu.mobile.torgilands.ui.theme.TorgiLandsTheme

class CActivityLandList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingPreview()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    var mytext by remember { mutableStateOf("") }
    //var text = mutableStateOf("")
    //var text = ""
    Column {
        OutlinedTextField(
            value = mytext,
            onValueChange = { mytext = it },
            label = { Text("Label") }
        )
        OutlinedTextField(
            value = mytext,
            onValueChange = { mytext = it },
            label = { Text("Label") }
        )
        Text(
            text = mytext
        )
    }

}