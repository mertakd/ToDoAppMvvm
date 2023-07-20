package com.example.todoappmvvm.fragments.list

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappmvvm.MainActivity
import com.example.todoappmvvm.R
import com.example.todoappmvvm.data.models.ToDoData
import com.example.todoappmvvm.data.viewmodel.ToDoViewModel
import com.example.todoappmvvm.databinding.FragmentListBinding
import com.example.todoappmvvm.fragments.SharedViewModel
import com.example.todoappmvvm.fragments.list.adapter.ListAdapter
import com.example.todoappmvvm.utils.observeOnce
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar



class ListFragment : Fragment(), SearchView.OnQueryTextListener{

    private val mToDoViewModel : ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Data binding
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView?.visibility = View.VISIBLE


        binding.lifecycleOwner = viewLifecycleOwner //binding nesnesi yaşam döngüsü sahibinin(bu sınıfat yaşam döngüsünü sahibi fragment) durumunu takip eder.(bu iki satır layout içerisinde data tag ı içinde yapılan işlem)
        binding.mBindingSharedViewModel = mSharedViewModel //viewmodel nesnesini, viewbinding nesnesine atar.





        //setup recyclerview
        setupRecyclerview()


        //livedata observe
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
            binding.recyclerView.scheduleLayoutAnimation()
        })

        /**
         * binding adapters sınıfı
         *
        mSharedViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
            showEmptyDatabaeViews(it)
        })

*/
    /**
     * binding adapters sınıfının içine yazıldı
     *
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        */


        /*//eski menü yapısı
        setHasOptionsMenu(true)*/

        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.list_fragment_menu, menu)

                val search = menu.findItem(R.id.menu_search)
                val searchView = search.actionView as? SearchView
                searchView?.isSubmitButtonEnabled = true
                searchView?.setOnQueryTextListener(this@ListFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_delete_all -> confirmRemoval()
                    R.id.menu_priority_high ->
                        mToDoViewModel.sortByHighPriority.observe(viewLifecycleOwner) {
                            adapter.setData(it)
                        }
                    R.id.menu_priority_low ->
                        mToDoViewModel.sortByLowPriority.observe(viewLifecycleOwner) {
                            adapter.setData(it)
                        }
                    R.id.alpha_up ->
                        mToDoViewModel.sortByAsc.observe(viewLifecycleOwner){
                            adapter.setData(it)
                        }
                    R.id.alpha_down ->
                        mToDoViewModel.sortByDsc.observe(viewLifecycleOwner){
                            adapter.setData(it)
                        }
                    android.R.id.home -> requireActivity().onBackPressed()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }






    private fun setupRecyclerview() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())


        //swipe to Delete
        swipeToDelete(recyclerView)
    }



    private fun swipeToDelete(recyclerView: RecyclerView) {

        val swipeToDeleteCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = adapter.dataList[viewHolder.adapterPosition]
                mToDoViewModel.deleteItem(deletedItem)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                //restore deleted item
                restoreDeletedData(viewHolder.itemView, deletedItem, viewHolder.adapterPosition)
            }

        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    private fun restoreDeletedData(view: View, deletedItem: ToDoData, position: Int){
        val snackbar = Snackbar.make(
            view, "Silindi '${deletedItem.title}'",
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Geri Al"){
            mToDoViewModel.insertData(deletedItem)
            adapter.notifyItemChanged(position)
        }
        snackbar.show()
    }



    /**
    * binding adapteers sınıfında daha kısa versiyonu yazıldı
    *
    private fun showEmptyDatabaeViews(emptyDatabase: Boolean) {
        if (emptyDatabase){
            binding.noDataImageView.visibility = View.VISIBLE
            binding.noDataTextView.visibility = View.VISIBLE
        }else{
            binding.noDataImageView.visibility = View.INVISIBLE
            binding.noDataTextView.visibility = View.INVISIBLE
        }
    }

*/



    /**
     * menu eski yapı
     *
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }*/

/**
 * menu eski yapı

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId ){
            R.id.menu_delete_all -> confirmRemoval()
            R.id.menu_priority_high -> mToDoViewModel.sortByHighPriority.observe(viewLifecycleOwner, Observer { adapter.setData(it) })
            R.id.menu_priority_low -> mToDoViewModel.sortByLowPriority.observe(viewLifecycleOwner, Observer { adapter.setData(it) })
        }
        return super.onOptionsItemSelected(item)
    }
*/

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null){
            searchThroughDatabase(query)
        }
        return true
    }


    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null){
            searchThroughDatabase(query)
        }
        return true
    }

    private fun searchThroughDatabase(query: String) {
        val searchQuery = "%$query%"


        mToDoViewModel.searchDatabase(searchQuery).observeOnce(viewLifecycleOwner, Observer { list ->
           list?.let {
               adapter.setData(it)
           }
        })
    }


    //alert dialog ile tüm notları silmek
    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Evet") {_, _ ->
            mToDoViewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Herşey Başarıyla Kaldırıldı!",
                Toast.LENGTH_SHORT
            ).show()

        }
        builder.setNegativeButton("Hayır"){_,_ ->}
        builder.setTitle("Herşey Silinsin Mi?")
        builder.setMessage("Her şeyi kaldırmak istediğinizden emin misiniz?")
        builder.create().show()
    }








    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}