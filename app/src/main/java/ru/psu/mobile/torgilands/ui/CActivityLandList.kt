package ru.psu.mobile.torgilands.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.ui.theme.Primary
import ru.psu.mobile.torgilands.ui.theme.Secondary

class CActivityLandList : ComponentActivity() {
    lateinit var resultLauncher : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPreview()
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data = result.data?.extras

                val x = 123
            }
        }
    }
    fun test()
    {
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
)
{

    val activity = LocalContext.current as CActivityLandList
    var menuState by remember { mutableStateOf(false) }



    TopAppBar(
        title = {
            Text(
                "Участки на торгах",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(onClick = {
                menuState = true
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Меню"
                )
            }
            DropdownMenu(
                expanded = menuState,
                onDismissRequest = { menuState = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Настройки") },
                    onClick = {
                        val intent = Intent(
                            activity,
                            CActivitySettings::class.java
                        ).apply {
                            // you can add values(if any) to pass to the next class or avoid using `.apply`
                            putExtra("MY_PARAM1", "123123123")
                        }
                        activity.resultLauncher.launch(intent)
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Settings,
                            contentDescription = "Настройки"
                        )
                    })
            }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun content(
    innerPadding : PaddingValues
)
{
    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(
                color = Secondary
            )
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(5.dp)
                .align(Alignment.Center)

        ) {
            OutlinedTextField(
                value = value1,
                onValueChange = { value1 = it },
                label = { Text("Число 1") },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = Primary,
                    focusedLabelColor = Primary,
                    unfocusedLabelColor = Primary
                )


            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = value2,
                onValueChange = { value2 = it },
                label = { Text("Число 2") },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(
                    color = Primary,
                    fontSize = 20.sp
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .height(50.dp)
            ){
                Button(
                    onClick = {
                        val val1 : Double= try{
                            value1.toDouble()
                        }
                        catch (e: Exception)
                        {
                            //Toast.
                            0.0
                        }
                        val val2 = try{
                            value2.toDouble()
                        }
                        catch (e: Exception)
                        {
                            //Toast.
                            0.0
                        }
                        result = ""+(val1+val2)

                    },
                    colors = ButtonDefaults
                        .buttonColors
                            (
                            containerColor = Primary),
//                modifier = Modifier
//                    .combinedClickable(
//                        onClick = { },
//                        onLongClick = { }
//                    )
                    shape = RoundedCornerShape(10),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentPadding = PaddingValues(5.dp)

                ) {
                    Icon(imageVector = Icons.Outlined.Add,
                        contentDescription = "")
                    Text(text="Сложить")
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_remove_24),
                        contentDescription = "")
                    Text(text="Вычесть")
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(text="Умножить")
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(text="Поделить")
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = result
            )
        }
        Image(
            painter = painterResource(R.drawable.house),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .width(200.dp)
                .height(180.dp)
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun MyPreview() {

    Scaffold(
        topBar = { MyTopBar() },
        content = { innerPadding ->
            content(innerPadding = innerPadding)
        }
    )





}