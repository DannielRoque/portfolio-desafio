package com.example.feednoticias.presentation.menu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.feednoticias.R
import com.example.feednoticias.data.entity.menu.MenuItem
import com.example.feednoticias.data.entity.menu.MenuResponse
import com.google.gson.Gson

internal class MenuViewModel(application: Application) : AndroidViewModel(application) {

    private val _menuItems = MutableLiveData<List<MenuItem>>()
    val menuItems: LiveData<List<MenuItem>> get() = _menuItems

    init {
        loadMenuItems()
    }

    private fun loadMenuItems() {
        val context = getApplication<Application>()
        val inputStream = context.resources.openRawResource(R.raw.menu)
        val json = inputStream.bufferedReader().use { it.readText() }

        val menuResponse: MenuResponse = Gson().fromJson(json, MenuResponse::class.java)
        _menuItems.value = menuResponse.menuItems
    }
}