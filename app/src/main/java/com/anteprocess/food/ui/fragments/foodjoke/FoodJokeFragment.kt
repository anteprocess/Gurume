package com.anteprocess.food.ui.fragments.foodjoke

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.anteprocess.food.R
import com.anteprocess.food.data.util.Constants.Companion.API_KEY
import com.anteprocess.food.data.util.NetworkResult
import com.anteprocess.food.databinding.FragmentFoodJokeBinding
import com.anteprocess.food.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [FoodJokeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


@AndroidEntryPoint
class FoodJokeFragment : Fragment() {

    // delegate to use mainview model
    private val mainViewModel by viewModels<MainViewModel>()

    private var _binding: FragmentFoodJokeBinding? = null
    private val binding get() = _binding!!


    private var foodJoke = "No Food Joke"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_food_joke, container, false)
        _binding = FragmentFoodJokeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.mainViewModel = mainViewModel

        setHasOptionsMenu(true)

        foodJoke = mainViewModel.getPresetFoodJoke()
        binding.foodjokeTextView.text = foodJoke
      //  mainViewModel.getFoodJoke(API_KEY)

//        mainViewModel.foodJokeResponse.observe(viewLifecycleOwner, { response ->
//            when(response) {
//                is NetworkResult.Success -> {
//                    binding.foodjokeTextView.text = response.data?.text
//                }
//                is NetworkResult.Error -> {
//                    loadDataFromCache()
//                    Toast.makeText(requireContext(),
//                    response.message.toString(),
//                    Toast.LENGTH_LONG).show()
//                }
//                is NetworkResult.Loading -> {
//                    Log.d("FoodJokeFragment", "Loading")
//                }
//            }
//        })

        return binding.root
    }


    //Option related
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       // super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.food_joke_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share_food_joke) {
            try {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, foodJoke)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            } catch (e: Exception) {
                Log.d("SHARE", "Share failed")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadDataFromCache() {
      lifecycleScope.launch {
          mainViewModel.readFoodJoke.observe(viewLifecycleOwner, {database ->
              if(database.isNotEmpty() && database != null) {
                  binding.foodjokeTextView.text = database[0].foodJoke.text
              }
          })
      }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // avoid memory leak
    }

}