package com.styni.simplecarbase.viewmodel.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.styni.simplecarbase.data.Brand
import com.styni.simplecarbase.data.Item
import com.styni.simplecarbase.data.Model
import com.styni.simplecarbase.di.App

class SearchViewModel : ViewModel() {

    private val _result: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    val result: MutableLiveData<List<Item>> = _result

    private lateinit var brands: ArrayList<Brand>
    private lateinit var models: ArrayList<Model>
    private val appContext = App.instance

    init{
        val jsonBrands = appContext.assets.open("brands.json").bufferedReader().use { it.readText() }
        val brandsListType = object : TypeToken<List<Brand>>() {}.type
        brands = Gson().fromJson(jsonBrands, brandsListType)

        val json = appContext.assets.open("models.json").bufferedReader().use { it.readText() }
        val itemsListType = object : TypeToken<List<Model>>() {}.type
        models = Gson().fromJson(json, itemsListType)
    }

    fun search(query: String?) {
        if (query.isNullOrBlank()) {
            return
        }

        var tempResult: ArrayList<Item> = arrayListOf()
        tempResult = brands.filter {
            it.name.toLowerCase().contains(query.toLowerCase())
        } as ArrayList<Item>


        tempResult.addAll(
            models.filter {
                it.name.toLowerCase().contains(query.toLowerCase())
            } as ArrayList<Item>
        )

        _result.value = tempResult
    }
}