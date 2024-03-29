package com.example.todoappmvvm.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoappmvvm.data.models.ToDoData

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id DESC")
    fun getAllData(): LiveData<List<ToDoData>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)


    @Update
    suspend fun updateData(toDoData: ToDoData)


    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()


    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>


    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<ToDoData>>


    @Query("SELECT * FROM todo_table ORDER BY title ASC")
    fun sortByAsc(): LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY title DESC")
    fun sortByDsc(): LiveData<List<ToDoData>>
}