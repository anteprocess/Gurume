package com.anteprocess.food.ui.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anteprocess.food.R
import com.anteprocess.food.adapters.IngredientsAdapter
import com.anteprocess.food.data.util.Constants.Companion.RECIPE_RESULT
import com.anteprocess.food.databinding.FragmentIngredientsBinding
import com.anteprocess.food.models.Result



class IngredientsFragment : Fragment() {

    private var _bindings: FragmentIngredientsBinding? = null
    private val binding get() = _bindings!!

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_ingredients, container, false)
        _bindings = FragmentIngredientsBinding.inflate(inflater, container, false)
        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT)

        setupRecyclerView()
        myBundle?.extendedIngredients?.let { mAdapter.setData(it) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindings = null
    }

    private fun setupRecyclerView() {
        binding.ingredientsRecyclerView.adapter = mAdapter
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}