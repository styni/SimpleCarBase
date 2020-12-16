package com.styni.simplecarbase.viewmodel.select_brand

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.styni.simplecarbase.di.App
import com.styni.simplecarbase.data.Brand


class SelectBrandViewModel : ViewModel() {

    private val appContext = App.instance

    private val _brands: MutableLiveData<ArrayList<Brand>> = MutableLiveData<ArrayList<Brand>>()
    val brands: MutableLiveData<ArrayList<Brand>> = _brands

    fun getBrands() {
        val jsonBrands = appContext.assets.open("brands.json").bufferedReader().use { it.readText() }
        val itemsListType = object : TypeToken<List<Brand>>() {}.type
        _brands.value = Gson().fromJson(jsonBrands, itemsListType)
    }
}