package com.anteprocess.food.ui.fragments.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.anteprocess.food.data.util.Constants
import com.anteprocess.food.databinding.FragmentInstructionsBinding
import com.anteprocess.food.models.Result

class InstructionsFragment : Fragment() {

    private var _bindings: FragmentInstructionsBinding? = null
    private val bindings get() = _bindings!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _bindings =  FragmentInstructionsBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT)

       bindings.instructionsWebview.webViewClient = object : WebViewClient() {}
        val websiteUrl: String = myBundle!!.sourceUrl
        bindings.instructionsWebview.loadUrl(websiteUrl)

        return bindings.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindings = null
    }

}