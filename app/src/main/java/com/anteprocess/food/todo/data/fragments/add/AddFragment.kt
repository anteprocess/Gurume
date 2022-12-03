package com.anteprocess.food.todo.data.fragments.add

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.anteprocess.food.R
import com.anteprocess.food.databinding.FragmentAddBinding
import com.anteprocess.food.databinding.FragmentListBinding
import com.anteprocess.food.todo.data.models.ToDoData
import com.anteprocess.food.todo.data.viewmodel.SharedViewModel
import com.anteprocess.food.todo.data.viewmodel.ToDoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()


    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_add, container, false)
        Log.d("TAG", "Add Fragment")
        _binding = FragmentAddBinding.inflate(inflater,container, false)
        binding.lifecycleOwner = this
        // Setting the color to the spinner
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    // For the priority selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            CoroutineScope(Dispatchers.Main).launch {
                insertDataToDb()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = binding.currentTitleEt.text.toString()
        val mPriority = binding.currentPrioritiesSpinner.selectedItem.toString()
        val mDescription = binding.currentDescriptionEt.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if (validation) {
            val newData = ToDoData(0,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added data", Toast.LENGTH_SHORT).show()
            // Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill in all data", Toast.LENGTH_SHORT).show()
        }
    }

}