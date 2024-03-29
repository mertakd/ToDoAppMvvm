package com.example.todoappmvvm.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoappmvvm.data.ToDoDatabase
import com.example.todoappmvvm.data.models.ToDoData
import com.example.todoappmvvm.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val repository: ToDoRepository


    val getAllData: LiveData<List<ToDoData>>
    //database imizdeki dataları liste şeklinde tutuyor.

    val sortByHighPriority: LiveData<List<ToDoData>>
    val sortByLowPriority: LiveData<List<ToDoData>>

    val sortByAsc: LiveData<List<ToDoData>>
    val sortByDsc: LiveData<List<ToDoData>>


    init {
        repository = ToDoRepository(toDoDao)
        getAllData = repository.getAllData

        sortByHighPriority = repository.sortByHighPriority
        sortByLowPriority = repository.sortByLowPriority

        sortByAsc = repository.sortByAsc
        sortByDsc = repository.sortByDsc
    }


    fun insertData(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateData(toDoData)
        }
    }

    fun deleteItem(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteItem(toDoData)
        }
    }


    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAll()
        }
    }

    fun  searchDatabase(searchQuery: String): LiveData<List<ToDoData>>{
        return repository.searchDatabase(searchQuery)
    }


    /**
     * ViewModel repository ve UI arasında kibir iletişim merkezi görevini görür.
     * ViewModel ise görsel arayüz(UI) ile model arasında köprü görevi görmektedir, yani Model’i View’a bağlayan yapıdır. View ile Model arasında doğrudan bir etkileşim yoktur. View, ilgili işlemleri ViewModel üzerinden yapmaktadır. ViewModel’ın View’a direkt erişimi yoktur ve View ile ilgili hiçbir şey bilmez.
     *
     */

}