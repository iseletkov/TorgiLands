package ru.psu.mobile.torgilands

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.psu.mobile.torgilands.ui.theme.Primary
import ru.psu.mobile.torgilands.ui.theme.Secondary
import ru.psu.mobile.torgilands.ui.theme.TorgiLandsTheme

class CActivityLandList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPreview()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MyPreview() {

    var mytext by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize(),

    ){
        Surface (
            modifier = Modifier
                .fillMaxSize(),
            color = Secondary
        )
        {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                ,

                ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = mytext,
                    onValueChange = { mytext = it },
                    label = { Text("Label") },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = Primary,
                        focusedLabelColor = Primary,
                        unfocusedLabelColor = Primary
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Primary
                        ),
                    value = mytext,
                    onValueChange = { mytext = it },
                    label = { Text("Label") }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 5.dp)
                            .fillMaxHeight()

                        //                    .combinedClickable (
                        //                        onCLick = {},
                        //                        onLongClick = {}
                        //                    )
                    ) {
                        Text("Plus")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = Primary
                            ),
                        shape = RoundedCornerShape(10)
                    ) {
                        Text("Minus")
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text("Multiply")
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text("Divide")
                    }
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = mytext
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.house),
            contentDescription = "123",
            modifier = Modifier
                .width(200.dp)
                .height(180.dp)
                .align(Alignment.BottomEnd)
        )
    }

}

