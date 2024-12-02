package com.example.feednoticias.presentation.portal.viewstate

import com.example.feednoticias.data.entity.PortalViewData

interface PortalState {
    object Loading : PortalState
    data class Success(val data: List<PortalViewData>) : PortalState
    data class Error(val message: String) : PortalState
}