package ru.psu.mobile.torgilands.ui.activitybottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.psu.mobile.torgilands.R
import ru.psu.mobile.torgilands.databinding.ActivityBottomNavigationBinding

//Исходный пример
//https://www.akshayrana.in/2020/07/bottom-navigation-bar-in-android.html
class CActivityBottomNavigation             : AppCompatActivity() {
    private lateinit var binding            : ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding                             = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openFragment(CFragmentAccountInfo())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_item_add_order -> {
                    openFragment(CFragmentAddOrder())
                    true
                }
                R.id.menu_item_order_list -> {
                    openFragment(CFragmentOrderList())
                    true
                }
                R.id.menu_item_profile -> {
                    openFragment(CFragmentAccountInfo())
                    true
                }
                else -> false
            }
        }
    }
    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction =  supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayoutContent, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}