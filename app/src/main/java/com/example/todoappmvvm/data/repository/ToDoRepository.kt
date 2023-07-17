package com.example.todoappmvvm.data.repository

import androidx.lifecycle.LiveData
import com.example.todoappmvvm.data.ToDoDao
import com.example.todoappmvvm.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {


    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()


    val sortByHighPriority: LiveData<List<ToDoData>> = toDoDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<ToDoData>> = toDoDao.sortByLowPriority()

    val sortByAsc: LiveData<List<ToDoData>> = toDoDao.sortByAsc()
    val sortByDsc: LiveData<List<ToDoData>> = toDoDao.sortByDsc()


    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }
    /**
     *  ToDoData nesnesi, veritabanına eklemek istediğimiz verileri içerir.Yani, bu kod, toDoData adlı bir ToDoData nesnesini alır ve
     bu nesneyi Room veritabanına eklemek için DAO arayüzündeki insertData() yöntemini çağırır. */


    suspend fun updateData(toDoData: ToDoData){
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoData: ToDoData){
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String):LiveData<List<ToDoData>>{
        return toDoDao.searchDatabase(searchQuery)
    }


    /**
     *Repository sınıfı, uygulamanın geri kalanına veri erişimi(data access) için temiz API sağlar

     */


}