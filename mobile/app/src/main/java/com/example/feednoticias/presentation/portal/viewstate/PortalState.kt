package com.example.feednoticias.presentation.portal.viewstate

import com.example.feednoticias.data.entity.PortalViewData

sealed class PortalState {
    data class Loading(val loading : Boolean) : PortalState()
    data class Success(val data: List<PortalViewData>) : PortalState()
    data class Error(val message: String) : PortalState()
}