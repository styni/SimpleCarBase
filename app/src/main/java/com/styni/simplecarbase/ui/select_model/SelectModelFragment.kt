package com.styni.simplecarbase.ui.select_model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.styni.simplecarbase.ui.global.BaseFragment
import com.styni.simplecarbase.R
import com.styni.simplecarbase.data.Item
import com.styni.simplecarbase.data.Model
import com.styni.simplecarbase.databinding.FragmentListBinding
import com.styni.simplecarbase.ui.detail_model.DetailModelFragment
import com.styni.simplecarbase.ui.global.SelectItemAdapter
import com.styni.simplecarbase.viewmodel.select_model.SelectModelViewModel

class SelectModelFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_list
    var binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentListBinding.bind(view!!)
        setToolbar(R.string.select_model, true, view, R.drawable.ic_back)
        binding?.modelViewModel = ViewModelProvider(this).get(SelectModelViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.list?.layoutManager = LinearLayoutManager(context)
        arguments?.getString(KEY_BRAND_ID)?.let {
            binding?.modelViewModel?.getModels(it)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        val owner = viewLifecycleOwner

        binding?.modelViewModel?.models?.observe(owner, Observer {
            binding?.list?.adapter = SelectItemAdapter(it as? ArrayList<Item>) {
                findNavController().navigate(R.id.detailModelFragment, Bundle().apply {
                    val model = it as Model
                    putString(DetailModelFragment.KEY_ENGINES, model.engines.toString())
                    putString(DetailModelFragment.KEY_GEN, model.gen.toString())
                    putString(DetailModelFragment.KEY_NAME, model.name)
                    putString(DetailModelFragment.KEY_YEAR, model.year.toString())
                })
            }
        })
    }

    override fun onHomeButtonPressed() {
        findNavController().navigateUp()
    }

    companion object {
        const val KEY_BRAND_ID = "brand_id"
    }
}