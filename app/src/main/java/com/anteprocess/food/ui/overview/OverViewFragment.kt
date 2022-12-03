package com.anteprocess.food.ui.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.anteprocess.food.models.Result
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.anteprocess.food.R
import com.anteprocess.food.data.util.Constants.Companion.RECIPE_RESULT
import com.anteprocess.food.databinding.FragmentOverViewBinding
import org.jsoup.Jsoup
/**
 * A simple [Fragment] subclass.
 * Use the [OverViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OverViewFragment : Fragment() {

    private var _binding : FragmentOverViewBinding? = null
    private val bindings get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentOverViewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT)
        bindings.mainImageView.load(myBundle?.image)

        bindings.titleTextView.text = myBundle?.title
        bindings.likesTextView.text = myBundle?.aggregateLikes.toString()
        bindings.timeTextView.text = myBundle?.readyInMinutes.toString()

        myBundle?.summary.let {
            val summary = Jsoup.parse(it).text()
            bindings.summaryTextView.text = summary
        }

        if (myBundle?.vegetarian == true) {
            bindings.vegetarianImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green ))
            bindings.vegetarianTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green ))
        }


        if (myBundle?.vegan == true) {
            bindings.veganImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green ))
            bindings.veganTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green ))
        }

        if (myBundle?.glutenFree == true) {
            bindings.glutenFreeImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green ))
            bindings.glutenFreeTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green ))
        }

        if (myBundle?.dairyFree == true) {
            bindings.dairyFreeImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green ))
            bindings.dairyFreeTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green ))
        }

        if (myBundle?.veryHealthy == true) {
            bindings.healthyImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green ))
            bindings.healthyTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green ))
        }

        if (myBundle?.cheap == true) {
            bindings.cheapImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green ))
            bindings.cheapTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green ))
        }


        return bindings.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}