package com.hadiid.znnews.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil{
    fun dateFormat(date: String): String{
        return if(date.isNullOrEmpty()) ""
        else{

            val currentFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val dateParse = currentFormat.parse(date)
            val toFormat = SimpleDateFormat("MMM, dd yyyy", Locale.getDefault())
            toFormat.format(dateParse)

        }
    }
}