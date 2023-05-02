package com.example.todoappmvvm.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoappmvvm.R
import com.example.todoappmvvm.data.viewmodel.ToDoViewModel
import com.example.todoappmvvm.databinding.FragmentListBinding


class ListFragment : Fragment(){

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val mToDoViewModel : ToDoViewModel by viewModels()
    private val adapter: ListAdapter by lazy {ListAdapter()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })



        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        binding.listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        //eski menü yapısı
        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}