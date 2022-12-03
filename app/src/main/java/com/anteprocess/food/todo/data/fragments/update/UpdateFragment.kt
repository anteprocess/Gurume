package com.anteprocess.food.todo.data.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.anteprocess.food.R
import com.anteprocess.food.databinding.FragmentUpdateBinding
import com.anteprocess.food.todo.data.models.ToDoData
import com.anteprocess.food.todo.data.viewmodel.SharedViewModel
import com.anteprocess.food.todo.data.viewmodel.ToDoViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()

    // Data binding
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        //val view =  inflater.inflate(R.layout.fragment_update, container, false)
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.args = args

        setHasOptionsMenu(true)


        //binding.currentTitleEt.setText(args.currentItem.title)
        //binding.currentDescriptionEt.setText(args.currentItem.description)
        //binding.currentPrioritiesSpinner.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
//        view?.let {
//            it.current_title_et.setText(args.currentItem.title)
//            it.current_description_et.setText(args.currentItem.description)
//            it.current_priorities_spinner.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
//            it.current_priorities_spinner.onItemSelectedListener = mSharedViewModel.listener
//        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    // Menu click events
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save) {
            updateItem()
        } else if (item.itemId == R.id.menu_delete) {
            confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = binding.currentTitleEt.text.toString()
        val desc = binding.currentDescriptionEt.text.toString()
        val priority = binding.currentPrioritiesSpinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, desc)
        if (validation) {
            // update current item
            val updateItem = ToDoData(
                args.currentItem.id,
                title = title,
                priority = mSharedViewModel.parsePriority(priority),
                description = desc
            )
            mToDoViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill in all data", Toast.LENGTH_SHORT).show()
        }
    }


    // show Remove item dialog
    private fun confirmItemRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mToDoViewModel.deleteItem(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentItem.title}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _->
        }
        builder.setTitle("Delete '${args.currentItem.title}' ")
        builder.setMessage("Are you sure you want to remove '${args.currentItem.title}' ")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}