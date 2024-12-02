package com.example.feednoticias.business.portal

import com.example.feednoticias.data.entity.PortalViewData
import com.example.feednoticias.data.repository.portal.PortalRepository

class PortalBusiness(
    private val portalRepository: PortalRepository

) {
    suspend fun loadInitialFeed(): List<PortalViewData> {
        val feed = portalRepository.getInitialFeed()

        return feed.filter { it.type in listOf("basico", "materia") }

            .map { item ->
                if (item.chapeu.isNullOrEmpty()) {
                    item.copy(chapeu = "Notícia")
                } else {
                    item
                }
            }
    }

    suspend fun loadMoreFeed(offerId: String, page: Int): List<PortalViewData> {
        val items = portalRepository.getPaginetedFeed(offerId, page)
        return items.filter { it.type in listOf("basico", "materia") }
            .map { item -> if (item.chapeu.isNullOrEmpty()) item.copy(chapeu = "Notícia") else item }
    }

}