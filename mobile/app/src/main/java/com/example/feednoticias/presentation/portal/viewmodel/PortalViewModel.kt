package com.example.feednoticias.presentation.portal.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.feednoticias.business.portal.PortalBusiness
import kotlinx.coroutines.launch

class PortalViewModel(application: Application,
    private val business : PortalBusiness) : AndroidViewModel(application) {

    fun loadInitialFeed() {
        viewModelScope.launch {
            try {
                val items = business.loadInitialFeed()
            } catch (e : Exception) {
                Log.e("PortalViewModel", "Error loading initial feed")
            } finally {

            }
        }
    }
}