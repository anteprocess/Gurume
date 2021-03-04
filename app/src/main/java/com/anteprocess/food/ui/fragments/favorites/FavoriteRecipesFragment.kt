package com.anteprocess.food.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anteprocess.food.R
import com.anteprocess.food.adapters.FavoriteRecipesAdapter
import com.anteprocess.food.databinding.FragmentFavoriteRecipesBinding
import com.anteprocess.food.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite_recipes.view.*


@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    private val mAdapter : FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter() }
    private val mainViewModel: MainViewModel by viewModels()

    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // val view =  inflater.inflate(R.layout.fragment_favorite_recipes, container, false)
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter

        setupRecyclerView(binding.favoriteRecipesRecyclerView)
//        mainViewModel.readFavoriteRecipes.observe(viewLifecycleOwner, { favoriteEntity ->
//            mAdapter.setData(favoriteEntity)
//        })

        return binding.root
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    }