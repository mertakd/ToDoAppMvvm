package com.example.todoappmvvm.data.models

import kotlin.math.min

data class TaskDateDto(
    val day: Int = 0,
    val month: Int = 0,
    val year: Int = 0,
    val hour: Int = 0,
    val minute: Int = 0
) {

    fun isDateReady(): Boolean {
        return day != 0 && month != 0 && year != 0
    }

    fun isTimeReady(): Boolean {
        return hour != 0
    }

    fun getDateTimeSt(): String {
        return "$day/$month/$year - $hour:$minute"
    }
}
