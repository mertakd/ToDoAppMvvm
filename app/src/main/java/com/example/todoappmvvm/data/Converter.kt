package com.example.todoappmvvm.data

import androidx.room.TypeConverter
import com.example.todoappmvvm.data.models.Priority

class Converter {

    //nesneyi string e çeviriyor
    @TypeConverter
    fun fromPriority(priority: Priority): String{
        return priority.name
    }


    //string i nesneye çeviriyor
    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}