package com.styni.simplecarbase.ui.search

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.styni.simplecarbase.R
import com.styni.simplecarbase.data.Brand
import com.styni.simplecarbase.data.Item
import com.styni.simplecarbase.data.Model
import com.styni.simplecarbase.databinding.FragmentSearchBinding
import com.styni.simplecarbase.ui.detail_model.DetailModelFragment
import com.styni.simplecarbase.ui.global.BaseFragment
import com.styni.simplecarbase.ui.global.SelectItemAdapter
import com.styni.simplecarbase.ui.select_model.SelectModelFragment
import com.styni.simplecarbase.viewmodel.search.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*

class SearchFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_search
    private var binding: FragmentSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSearchBinding.bind(view!!)
        binding?.viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        setToolbar(R.string.search, true, view, R.drawable.ic_baseline_menu_24)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.searchList?.layoutManager = LinearLayoutManager(context)
        binding?.searchList?.adapter = SelectItemAdapter(arrayListOf()) {
            if (it is Model) {
                findNavController().navigate(R.id.detailModelFragment, Bundle().apply {
                    val model = it
                    putString(DetailModelFragment.KEY_ENGINES, model.engines.toString())
                    putString(DetailModelFragment.KEY_GEN, model.gen.toString())
                    putString(DetailModelFragment.KEY_NAME, model.name)
                    putString(DetailModelFragment.KEY_YEAR, model.year.toString())
                })
            }
            if (it is Brand) {
                findNavController().navigate(R.id.selectModelFragment, Bundle().apply {
                    putString(SelectModelFragment.KEY_BRAND_ID, it.id.hash)
                })
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        val owner = viewLifecycleOwner

        binding?.viewModel?.result?.observe(owner, {
            if (it.isNullOrEmpty()) {
                binding?.tvNotFound?.visibility = View.VISIBLE
            } else {
                binding?.tvNotFound?.visibility = View.GONE
            }

            (binding?.searchList?.adapter as SelectItemAdapter).setData(it as? ArrayList<Item>)
        })
    }

    override fun onHomeButtonPressed() {
        activity?.drawer?.openDrawer(GravityCompat.START)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val search = menu.findItem(R.id.app_bar_search)
        val searchView = search.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                binding?.viewModel?.search(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}