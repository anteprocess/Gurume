package com.anteprocess.food.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.anteprocess.food.R
import com.anteprocess.food.data.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.anteprocess.food.data.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.anteprocess.food.databinding.RecipesBottomSheetBinding
import com.anteprocess.food.viewmodels.RecipesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.lang.Exception
import java.util.*

/**
 * A simple [BottomSheetDialogFragment] subclass.
 * Use the [RecipesBottomSheet.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipesBottomSheet : BottomSheetDialogFragment() {

    private var _binding: RecipesBottomSheetBinding? = null
    private val bindings get() = _binding!!


    //chips
    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    // We are going to be using the recipes view model in here
    private lateinit var recipesViewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         _binding = RecipesBottomSheetBinding.inflate(inflater, container, false)


        // We will get the latest data and display. Flow type
        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner, { value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType
            updateChip(value.selectedMealTypeId, bindings.mealTypeChipGroup)
            updateChip(value.selectedDietTypeId, bindings.dietTypeChipGroup)
        })

        //get the references to the chipGroup for the meal type
        bindings.mealTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
            //selected chip id
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedMealType = chip.text.toString().toLowerCase(Locale.ROOT)
            // store the text of the selected chip
            mealTypeChip = selectedMealType
            mealTypeChipId = selectedChipId
        }

        bindings.dietTypeChipGroup.setOnCheckedChangeListener { group, selectedDietChipId ->
            val chip = group.findViewById<Chip>(selectedDietChipId)
            val selectedDietType = chip.text.toString().toLowerCase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = selectedDietChipId
        }

        // Clicking the apply button
        bindings.applyBtn.setOnClickListener {
            recipesViewModel.saveMealAndDietType(
                mealTypeChip, mealTypeChipId,
                dietTypeChip, dietTypeChipId
            )
            // pass the boolean to RecipesFragment
            val action =
                RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(true)
            findNavController().navigate(action)
        }

        return bindings.root
    }

    // Update the chip group
    private fun updateChip(chipid: Int, chipGroup: ChipGroup) {
        if (chipid != 0) {
            try {
                chipGroup.findViewById<Chip>(chipid).isChecked = true
            } catch (e: Exception) {
                Log.d("RecipesBottomSheet", e.message.toString());
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}