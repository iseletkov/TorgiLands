package ru.psu.mobile.torgilands

import android.app.Application
import android.content.Context

class CApplication : Application()
{
    companion object{
        private lateinit var context : Context

        fun getContext() : Context
        {
            return context
        }
    }

}