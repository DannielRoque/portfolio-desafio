package com.example.feednoticias.presentation.portal.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feednoticias.business.portal.PortalBusiness
import com.example.feednoticias.presentation.portal.viewstate.PortalState
import kotlinx.coroutines.launch

class PortalViewModel(
    application: Application,
    private val business: PortalBusiness
) : AndroidViewModel(application) {

    private val _feedState = MutableLiveData<PortalState>()
    val feedState: LiveData<PortalState> get() = _feedState

    fun loadInitialFeed() {
        viewModelScope.launch {
            try {
                _feedState.value = PortalState.Loading(loading = true)
                _feedState.value = PortalState.Success(data = business.loadInitialFeed())
            } catch (e: Exception) {
                Log.e("PortalViewModel", "Error loading initial feed")
            } finally {
                _feedState.value = PortalState.Loading(loading = false)
            }
        }
    }

    fun loadNextPage() {
        viewModelScope.launch {
            try {
                _feedState.value = PortalState.Loading(loading = true)
                val response = business.loadNextPage(page = 1)
                val currentData = (_feedState.value as? PortalState.Success)?.data ?: emptyList()
                _feedState.value = PortalState.Success(data = currentData + response)
            } catch (e : Exception) {
                Log.e("PortalViewModel", "Error loading next page")
            } finally {
                _feedState.value = PortalState.Loading(loading = false)
            }
        }

    }
}