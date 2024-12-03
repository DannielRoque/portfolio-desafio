package com.example.feednoticias.business.portal

import com.example.feednoticias.data.entity.PortalViewData

interface PortalBusiness {

    suspend fun loadInitialFeed(): List<PortalViewData>
    suspend fun loadNextPage(page: Int): List<PortalViewData>
}