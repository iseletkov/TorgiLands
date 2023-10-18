package ru.psu.mobile.torgilands

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

fun test(
    a1 : String,
    a2 : String = "hhhhhh"
) : String
{
    return a1+a2
}

class MainActivity : AppCompatActivity() {
    var f : Toast?
        = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val x= 0.0
        val y = 123
        var z = 345
        var str = "adawdawdawd"
        var name = "Иван"
        var str2 = ""

        var lst1 = listOf("awd", "adawd", "adawdsfsef")
        var lst2 = mutableListOf("awd", null, "adawd", "gggggg", null, "adawdsfsef", "ffffff")

        val message = lst2
            .filterNotNull()
            .filter {
                it.startsWith("a") }
            .map {
                "123$it"
            }
            .joinToString(separator = "; ")


        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()



    }
}