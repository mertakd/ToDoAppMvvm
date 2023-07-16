package com.example.todoappmvvm.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappmvvm.data.models.ToDoData
import com.example.todoappmvvm.databinding.RowLayoutBinding
import com.example.todoappmvvm.fragments.add.ToDoDiffUtil

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {



    var dataList = emptyList<ToDoData>()



    class MyViewHolder(private val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(toDoData: ToDoData){
            binding.toDoDataBinding = toDoData
            binding.executePendingBindings()
            //row layout ile bind ediyoruz.
        }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }



    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = dataList[position]
        holder.bind(currentItem)
    }




/*eski kullanımı

*   override fun onBindViewHolder(holder:MyViewHolder, position: Int){
        holder.itemView.title_txt.text = dataList[position].title
        holder.itemView.description_txt.text = dataList[position].description

        val priority = dataList[position].priority

    }
 */


    fun setData(toDoData: List<ToDoData>){
        val toDoDiffUtil = ToDoDiffUtil(dataList, toDoData)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = toDoData
        toDoDiffResult.dispatchUpdatesTo(this)
       /* this.dataList = toDoData
        notifyDataSetChanged()
        //notifyDataSetChanged() biz bir veriyi güncellesek bile notifyDataSetChanged() fonksiyonu recyclervie daki tüm item ları/tüm verileri güncelliyor view çağırıldığında.performanslı değil.bu yüzden diffutil i kullanmalıyız.
        //diffutil recyclerview listesinde birçok item olsa bile sadece güncellenen item ı günceller, tüm listeyi güncellemez.daha performanslı*/
    }

}