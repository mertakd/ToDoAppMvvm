package com.example.todoappmvvm.data.repository

import androidx.lifecycle.LiveData
import com.example.todoappmvvm.data.ToDoDao
import com.example.todoappmvvm.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {


    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()


    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }

}