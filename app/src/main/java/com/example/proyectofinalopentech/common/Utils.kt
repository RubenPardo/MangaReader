package com.example.proyectofinalopentech.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class Utils {
    companion object{
        fun parseStrigDateToDate(dateString: String?):Date?{
            try{
                if(dateString.isNullOrEmpty()) return null
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
                dateFormat.timeZone = TimeZone.getTimeZone("UTC")

                return if(dateString!=null) dateFormat.parse(dateString) else null
            }catch (e:Exception){
                return null
            }
        }
    }
}