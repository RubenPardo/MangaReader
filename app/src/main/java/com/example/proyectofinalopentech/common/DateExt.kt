package com.example.proyectofinalopentech.common

import java.text.SimpleDateFormat
import java.util.Date

fun Date.format():String{
    val outputFormat = SimpleDateFormat("dd-MM-yyyy")
    return outputFormat.format(this)
}