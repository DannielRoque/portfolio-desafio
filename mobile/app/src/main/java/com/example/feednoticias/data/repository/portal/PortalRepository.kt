package com.example.feednoticias.data.repository.portal

import com.example.feednoticias.data.api.portal.PortalApi
import com.example.feednoticias.data.db.painel.PortalDao
import com.example.feednoticias.data.entity.PortalViewData

class PortalRepository(
    private val api: PortalApi,
    private val newsDao: PortalDao
) {
    suspend fun getInitialFeed(): List<PortalViewData> {
        return try {
            val response = api.getInitialFeed()
            val items = response.map { apiItem ->
                PortalViewData(
                    id = apiItem.id,
                    titulo = apiItem.titulo,
                    descricao = apiItem.descricao,
                    imagem = apiItem.imagem,
                    chapeu = apiItem.chapeu,
                    date = apiItem.date,
                    url = apiItem.url,
                    type = apiItem.type
                )
            }

            // Salva no banco local
            newsDao.deleteAllPortalData()
            newsDao.insertPortalData(items)
            items
        } catch (e: Exception) {
            // Retorna dados locais se a API falhar
            newsDao.getAllPortalData()
        }
    }

    suspend fun getPaginetedFeed(offerId: String, page: Int): List<PortalViewData> {
        TODO("Not yet implemented")
    }
}