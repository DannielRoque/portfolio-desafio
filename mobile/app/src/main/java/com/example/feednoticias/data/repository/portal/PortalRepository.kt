package com.example.feednoticias.data.repository.portal

import com.example.feednoticias.data.entity.PortalViewData

interface PortalRepository {
    suspend fun getInitialFeed(): List<PortalViewData>
    suspend fun getPaginetedFeed(offerId: String, page: Int): List<PortalViewData>
}