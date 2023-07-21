package com.example.todoappmvvm.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoappmvvm.data.models.Priority
import kotlinx.parcelize.Parcelize
import java.time.OffsetDateTime

@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData (
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    var title : String,
    var priority: Priority,
    var description : String

    ):Parcelable