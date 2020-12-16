package com.styni.simplecarbase.ui.detail_model

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.styni.simplecarbase.ui.global.BaseFragment
import com.styni.simplecarbase.R
import com.styni.simplecarbase.databinding.FragmentDetailModelBinding
import com.styni.simplecarbase.viewmodel.detail_model.DetailModelViewModel

class DetailModelFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_detail_model
    private var binding: FragmentDetailModelBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailModelBinding.bind(view!!)
        binding?.viewModel = ViewModelProvider(this).get(DetailModelViewModel::class.java)

        setToolbar(R.string.select_model, true, view, R.drawable.ic_back)

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name = arguments?.getString(KEY_NAME)
        val year = arguments?.getString(KEY_YEAR)
        val engines = arguments?.getString(KEY_ENGINES)?.replace("]","")?.replace("[","")
        val gen = arguments?.getString(KEY_GEN)?.replace("]","")?.replace("[","")

        binding?.name?.text = getString(R.string.name) + ": $name"
        binding?.year?.text = getString(R.string.year) + ": $year"
        binding?.gen?.text = getString(R.string.gen) + ": $gen"
        binding?.engines?.text = getString(R.string.engines) + ": $engines"
    }

    override fun onHomeButtonPressed() {
        findNavController().navigateUp()
    }

    companion object {
        const val KEY_NAME = "key_name"
        const val KEY_YEAR = "key_year"
        const val KEY_ENGINES = "key_engines"
        const val KEY_GEN = "key_gen"
    }
}