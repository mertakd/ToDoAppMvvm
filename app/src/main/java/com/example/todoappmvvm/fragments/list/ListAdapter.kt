package com.example.todoappmvvm.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappmvvm.R
import com.example.todoappmvvm.data.models.Priority
import com.example.todoappmvvm.data.models.ToDoData
import com.example.todoappmvvm.databinding.RowLayoutBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {



    var dataList = emptyList<ToDoData>()



    inner class MyViewHolder(private val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(toDoData: ToDoData){
            binding.titleTxt.text = dataList[adapterPosition].title
            binding.descriptionTxt.text = dataList[adapterPosition].description
            binding.rowBackground.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
                binding.root.findNavController().navigate(action)
            }

            when(dataList[adapterPosition].priority){
                Priority.HIGH -> binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(
                    binding.root.context,
                    R.color.red))
                Priority.MEDIUM -> binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(
                    binding.root.context,
                    R.color.yellow))
                Priority.LOW -> binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(
                    binding.root.context,
                    R.color.green))
            }
        }



    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }


    fun setData(toDoData: List<ToDoData>){
        this.dataList = toDoData
        notifyDataSetChanged()
    }

}