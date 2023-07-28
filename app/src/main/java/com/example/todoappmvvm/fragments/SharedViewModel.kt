package com.example.todoappmvvm.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoappmvvm.R
import com.example.todoappmvvm.data.models.Priority
import com.example.todoappmvvm.data.models.ToDoData

class SharedViewModel(application: Application) : AndroidViewModel(application){

    //list fragment da not yok resim ve yazısı ile ilgili logic
    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDatabaseEmpty(toDoData: List<ToDoData>){
        emptyDatabase.value = toDoData.isEmpty()
        //dataList empty ise true, yani default durumu true
        //boş değilse false
    }



    //spinner daki textview ın yazı rengini değiştiriyoruz
    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when(position){
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red)) }
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow)) }
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green)) }
            }
        }
    }






    /*fun verifyDataFromUser(title: String, description:String):Boolean{
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
            false
        }else !(title.isEmpty() || description.isEmpty())
    }*/
    //add ve update işlemi için kullanılıyor.
    fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }





    //stringden nesneye dönüştürülüyor.
    //add ve update fragment da insert ve update işlemi yaparken kullanılıyor
    fun parsePriority(priority: String): Priority {
        return when(priority){
            "Yüksek Öncelik" -> {
                Priority.HIGH}
            "Orta Öncelik" -> {
                Priority.MEDIUM}
            "Düşük Öncelik" -> {
                Priority.LOW}
            else -> Priority.LOW

        }
    }


/*

    //bu logic bindingadapter a taşındı:d

    //PPRİORİTY nesnesini integer a dönüştürüyor.
    fun parsePriorityToInt(priority: Priority) : Int{
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }

*/

}