package ru.psu.mobile.torgilands.ui.activitylandlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.model.CLand
import ru.psu.mobile.torgilands.ui.CActivitySettings
import ru.psu.mobile.torgilands.ui.activitylanddetail.CActivityLandDetails
import java.util.UUID

class CActivityLandListCompose              : ComponentActivity() {
    lateinit var resultLauncher             : ActivityResultLauncher<Intent>
    var items                               = mutableStateListOf<CLand>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CPreview()
        }
        items.add(
            CLand(
                UUID.randomUUID(),
                "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
                123456.00,
                1200.0,
                "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
            )
        )
        items.add(
            CLand(
                UUID.randomUUID(),
                "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
                123456.00,
                1200.0,
                "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
            )
        )
        items.add(
            CLand(
                UUID.randomUUID(),
                "Предмет аукциона – земельный участок, государственная собственность на который не разграничена, с кадастровым номером 12:04:0870113:991, категория земель – земли населенных пунктов, разрешенное использование – обслуживание автотранспорта, площадью 1345 кв. м., расположенный по адресу: Российская Федерация, Республика Марий Эл, Медведевский муниципальный район, пгт. Медведево, в границах, соответствующих описанию в сведениях государственного кадастра недвижимости (далее – земельный участок).",
                123456.00,
                1200.0,
                "Иной вид (установлен до дня утверждения в соответствии с ЗК РФ классификатора)"
            )
        )
        items.add(
            CLand(
                UUID.randomUUID(),
                "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
                123456.00,
                1200.0,
                "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
            )
        )
        items.add(
            CLand(
                UUID.randomUUID(),
                "Аренда земельного участка 1 200 кв.м для ЛПХ в г.о. Домодедово (КН 50:28:0110318:1226)",
                123456.00,
                1200.0,
                "Для ведения личного подсобного хозяйства (приусадебный земельный участок)"
            )
        )

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.resultCode != Activity.RESULT_OK)
                    return@registerForActivityResult

                // There are no request codes
                val bundle                  = result.data?.extras
                bundle?.let{
                    val id                  = bundle.getString(getString(R.string.PARAM_ID))?.let { tempId ->
                        UUID.fromString(tempId)
                    }

                    id?: return@registerForActivityResult

                    val header              = bundle.getString(getString(R.string.PARAM_HEADER), "")
                    val type                = bundle.getString(getString(R.string.PARAM_TYPE), "")
                    val price               = bundle.getDouble(getString(R.string.PARAM_PRICE))
                    val square              = bundle.getDouble(getString(R.string.PARAM_SQUARE))

                    val index               = items.indexOfFirst { it.id==id }
                    if (index>=0)
                    {
                        items[index]        = items[index].copy(
                            header = header,
                            type = type,
                            price = price,
                            square = square
                        )
                    }
                    else
                    {
                        items.add(
                            CLand(
                                id ,
                                header,
                                price,
                                square,
                                type
                            )
                        )
                    }


                }
            }
        }
    }
    fun onButtonAddClick()
    {
        val intent                  = Intent(
            this,
            CActivityLandDetails::class.java
        )
        resultLauncher.launch(intent)
    }
    fun onItemEdit(
        land                                : CLand
    )
    {
        val intent                          = Intent(
            this,
            CActivityLandDetails::class.java
        )

        intent.putExtra(getString(R.string.PARAM_ID), land.id.toString())
        intent.putExtra("PARAM_HEADER", land.header)
        intent.putExtra("PARAM_TYPE", land.type)
        intent.putExtra("PARAM_PRICE", land.price)
        intent.putExtra("PARAM_SQUARE", land.square)
        resultLauncher.launch(intent)
    }
}
@Composable
fun Menu(
    menuState                               : Boolean,
    updateState                             : (
        state                               : Boolean
    )                                       -> Unit
)
{
    val activity                            = LocalContext.current as CActivityLandListCompose

    DropdownMenu(
        expanded                            = menuState,
        onDismissRequest                    = { updateState(false) }
    ) {
        DropdownMenuItem(
            text                            = { Text("Настройки") },
            onClick                         = {
                val intent                  = Intent(
                    activity,
                    CActivitySettings::class.java
                ).apply {
                    // you can add values(if any) to pass to the next class or avoid using `.apply`
                    putExtra("MY_PARAM1", "123123123")
                }
                activity.resultLauncher.launch(intent)
            },
            leadingIcon                     = {
                Icon(
                    Icons.Outlined.Settings,
                    contentDescription      = "Настройки"
                )
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
)
{
    var menuState by remember { mutableStateOf(false) }
    TopAppBar(
        title                               = {
            Text(
                "Участки на торгах",
                maxLines                    = 1,
                overflow                    = TextOverflow.Ellipsis
            )
        },
        actions                             = {
            IconButton(
                onClick                     = {
                    menuState               = true
                }
            ) {
                Icon(
                    imageVector             = Icons.Filled.Menu,
                    contentDescription      = "Меню"
                )
            }
            Menu(menuState){
                menuState                   = it
            }
        }
    )
}
@Composable
fun LandCard(
    land                                    : CLand
)
{
    val activity                            = LocalContext.current as CActivityLandListCompose

    Column(
        modifier                            = Modifier.clickable {
            activity.onItemEdit(land)
        }
    ){
        Text(text                           = land.header)
        Row {
            Image(
                painter                     = painterResource(
                    id                      = R.drawable.house
                ),
                contentDescription          = "Фото участка",
                modifier                    = Modifier.size(100.dp, 100.dp)
            )
            Column {
                Text(text = String.format("%.2f руб", land.price))
                Text(text = String.format("%.2f кв.м.", land.square))
                Text(text = land.type)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    innerPadding                            : PaddingValues
)
{
    val activity                            = LocalContext.current as CActivityLandListCompose

    LazyColumn(
        contentPadding                      = innerPadding,
        verticalArrangement                 = Arrangement.spacedBy(9.dp)
    ) {
        items(items = activity.items){ item ->
            LandCard(item)
        }
    }
}
//https://stackoverflow.com/questions/58547448/jetpack-ui-compose-how-to-create-floatingactionbutton
@Composable
fun Fab()
{
    val activity                            = LocalContext.current as CActivityLandListCompose
    FloatingActionButton(
        onClick                             = {
            activity.onButtonAddClick()
        }
    ) {
        Icon(Icons.Filled.Add,"")
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun CPreview() {

    Scaffold(
        topBar                              = { MyTopBar() },
        content                             = { innerPadding ->
            Content(innerPadding            = innerPadding)
        },
        floatingActionButton                = { Fab() }
    )
}