package com.styni.simplecarbase.viewmodel.select_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.styni.simplecarbase.data.Model
import com.styni.simplecarbase.di.App

class SelectModelViewModel : ViewModel() {

    private val appContext = App.instance

    private val _models: MutableLiveData<List<Model>> = MutableLiveData<List<Model>>()
    val models: MutableLiveData<List<Model>> = _models

    fun getModels(brandId: String) {
        val json = appContext.assets.open("models.json").bufferedReader().use { it.readText() }
        val itemsListType = object : TypeToken<ArrayList<Model>>() {}.type
        val allModels: ArrayList<Model> = Gson().fromJson(json, itemsListType)
        _models.value = allModels.filter {
            it.brandId.hash == brandId
        }
    }
}