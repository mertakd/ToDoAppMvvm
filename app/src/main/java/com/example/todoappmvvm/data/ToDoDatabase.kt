package com.example.todoappmvvm.data

import android.content.Context
import androidx.room.*
import com.example.todoappmvvm.data.models.ToDoData


@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object{

        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
                //instance a sahip olduğumuzda aynı instance ı dönüyor.aynı örneği döndürmek istiyoruz, birçok örnek oluşursa performans sorunları çıkar.
            }

            synchronized(this){
                //birden fazla thread kullanmak istemiyoruz o yüzden syn fonk kullanılıyor.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }



    }

}