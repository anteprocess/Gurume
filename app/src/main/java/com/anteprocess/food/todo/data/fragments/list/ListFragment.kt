package com.anteprocess.food.todo.data.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.anteprocess.food.R
import com.anteprocess.food.data.util.observeOnce
import com.anteprocess.food.databinding.FragmentListBinding
import com.anteprocess.food.todo.data.models.ToDoData
import com.anteprocess.food.todo.data.viewmodel.SharedViewModel
import com.anteprocess.food.todo.data.viewmodel.ToDoViewModel
import com.anteprocess.todo.fragments.list.adapter.ListAdapter
import com.anteprocess.todo.fragments.list.adapter.SwipeToDelete
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private val adapter: ListAdapter by lazy { ListAdapter() }

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    _binding = FragmentListBinding.inflate(inflater,container, false)
    binding.lifecycleOwner = this
    binding.mSharedViewModel = mSharedViewModel
    // Setup recyclerview
    setupRecyclerView()

    // Getting all the items from the database
    mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
        mSharedViewModel.checkIfdatabaseEmpty(data)
        adapter.setData(data)
    })
    //set menu
    setHasOptionsMenu(true)
    // Hide soft keyboard
        //hideKeyboard(requireActivity())
    return binding.root
}

private fun setupRecyclerView() {
    val recyclerView = binding.recyclerView
    recyclerView.adapter = adapter
    // recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//    recyclerView.itemAnimator = LandingAnimator().apply {
//        addDuration = 300
//    }
    // setting the swipe to delete
    swipeToDelete(recyclerView)
}

// Method to call the swipe to delete
private fun swipeToDelete(recyclerView: RecyclerView) {
    val swipeToDeleteCallback = object : SwipeToDelete() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val deletedItem = adapter.dataList[viewHolder.adapterPosition]
            mToDoViewModel.deleteItem(deletedItem)
            adapter.notifyItemRemoved(viewHolder.adapterPosition)
            restoreDeletedData(viewHolder.itemView, deletedItem)
        }
    }
    val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
    itemTouchHelper.attachToRecyclerView(recyclerView)
}


private fun restoreDeletedData(view: View, deletedItem: ToDoData) {
    val snackbar = Snackbar.make(
        view,
        "Deleted",
        Snackbar.LENGTH_LONG
    )
    snackbar.setAction("Undo") {
        mToDoViewModel.insertData(deletedItem)
    }
    snackbar.show()
}

// Custom menu options
@Deprecated("Deprecated in Java")
override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    //super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.listfragment_menu, menu)

    // Setting up the search
    val search: MenuItem = menu.findItem(R.id.menu_search)
    val searchView: SearchView = search.actionView as SearchView
    searchView.isSubmitButtonEnabled = true
    searchView.setOnQueryTextListener(this)

}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId) {
        R.id.menu_delete_all -> { confirmRemoval() }
        R.id.menu_priority_high -> { mToDoViewModel.sortByHighPriority.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        }) }
        R.id.menu_priority_low -> {mToDoViewModel.sortByLowPriority.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        }) }
        R.id.testroom -> {
//            val action =
//                ListFragmentDirections.actionListFragmentToRoomTestFragment()
//            findNavController().navigate(action)
        }
    }

    return super.onOptionsItemSelected(item)
}

private fun confirmRemoval() {
    val builder = AlertDialog.Builder(requireContext())
    builder.setPositiveButton("Yes") { _, _ ->
        mToDoViewModel.deleteAll()
        Toast.makeText(
            requireContext(),
            "Successfully removed all items",
            Toast.LENGTH_SHORT).show()
    }
    builder.setNegativeButton("No") { _, _->
    }
    builder.setTitle("Delete All items")
    builder.setMessage("Are you sure you want to remove all items?")
    builder.create().show()
}

override fun onDestroyView() {
    super.onDestroyView()
    // Prevent the binding leaks
    _binding = null
}

override fun onQueryTextSubmit(query: String?): Boolean {
    if (query != null ) {
        searchThroughDatabase(query)
    }
    return true
}

override fun onQueryTextChange(query: String?): Boolean {
    if (query != null ) {
        searchThroughDatabase(query)
    }
    return true
}

private fun searchThroughDatabase(query: String) {
    var searchQuery: String = query
    searchQuery = "%$searchQuery%"
    mToDoViewModel.searchDatabase(searchQuery).observeOnce(viewLifecycleOwner, Observer { list ->
        list?.let {
            adapter.setData(it)
        }
    })
}

}