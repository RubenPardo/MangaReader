package com.example.proyectofinalopentech.common

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date


class UtilsTest{
    @Test
    fun `WHEN try to parse empty string to date EXPECT null`(){
        val stringDate = ""
        val res = Utils.parseStrigDateToDate(stringDate)

        assertEquals(res == null,true)
    }

    @Test
    fun `WHEN try to parse wrong string to date EXPECT null`(){
        val stringDate = "dssgfsg"
        val res = Utils.parseStrigDateToDate(stringDate)

        assertEquals(res == null,true)
    }

    @Test
    fun `WHEN try to parse correct string to date EXPECT correct Date object`(){
        val stringDate = "2023-08-01T15:47:38+00:00"
        val res = Utils.parseStrigDateToDate(stringDate)

        assertEquals(res is Date,true)
    }
}