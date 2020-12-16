package com.styni.simplecarbase.ui.select_brand

import android.os.Bundle
import android.view.*
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.styni.simplecarbase.ui.global.BaseFragment
import com.styni.simplecarbase.R
import com.styni.simplecarbase.data.Item
import com.styni.simplecarbase.databinding.FragmentListBinding
import com.styni.simplecarbase.ui.global.SelectItemAdapter
import com.styni.simplecarbase.ui.select_model.SelectModelFragment
import com.styni.simplecarbase.viewmodel.select_brand.SelectBrandViewModel
import kotlinx.android.synthetic.main.activity_main.*

class SelectBrandFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_list
    var binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentListBinding.bind(view!!)
        binding?.brandViewModel = ViewModelProvider(this).get(SelectBrandViewModel::class.java)
        setToolbar(R.string.select_brand, true, view, R.drawable.ic_baseline_menu_24)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.list?.layoutManager = LinearLayoutManager(context)

        binding?.brandViewModel?.getBrands()

        observeViewModel()
    }

    override fun onHomeButtonPressed() {
        activity?.drawer?.openDrawer(GravityCompat.START)
    }

    private fun observeViewModel() {
        val owner = viewLifecycleOwner

        binding?.brandViewModel?.brands?.observe(owner, {
            binding?.list?.adapter = SelectItemAdapter(it as? ArrayList<Item>) {
                findNavController().navigate(R.id.selectModelFragment, Bundle().apply {
                    putString(SelectModelFragment.KEY_BRAND_ID, it.id.hash)
                })
            }
        })
    }
}