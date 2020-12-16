package com.styni.simplecarbase.ui.global

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.styni.simplecarbase.R

abstract class BaseFragment : Fragment() {

    abstract val layoutRes: Int
    var toolbar: MaterialToolbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    protected fun setToolbar(@StringRes title: Int, withIcon: Boolean, view: View, @DrawableRes icon: Int) {
        toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setTitle(title)
        if (withIcon) {
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            toolbar?.setNavigationOnClickListener { onHomeButtonPressed() }
            actionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(requireContext(), icon))
        }
    }

    protected fun setToolbar(title: String, withIcon: Boolean, view: View, @DrawableRes icon: Int) {
        toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = title
        if (withIcon) {
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            toolbar?.setNavigationOnClickListener { onHomeButtonPressed() }
            actionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(requireContext(), icon))
        }
    }


    open fun onHomeButtonPressed() {
    }

    override fun onDestroy() {
        toolbar?.setNavigationOnClickListener(null)
        super.onDestroy()
    }
}