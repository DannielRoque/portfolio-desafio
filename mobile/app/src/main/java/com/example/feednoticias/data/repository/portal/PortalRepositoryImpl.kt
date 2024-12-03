package com.example.feednoticias.data.repository.portal

import com.example.feednoticias.data.api.portal.PortalApi
import com.example.feednoticias.data.db.painel.PortalDao
import com.example.feednoticias.data.entity.PortalViewData

class PortalRepositoryImpl(
    private val api: PortalApi,
    private val portalDao: PortalDao
) : PortalRepository {

    override suspend fun getInitialFeed(): List<PortalViewData> {
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

            portalDao.deleteAllPortalData()
            portalDao.insertPortalData(items)
            items
        } catch (e: Exception) {
            portalDao.getAllPortalData()
        }
    }

    override suspend fun getPaginetedFeed(page: Int): List<PortalViewData> {
        return try {
            val response = api.getPaginatedFeed(product = "g1", page = page)
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

            portalDao.insertPortalData(items)
            items
        } catch (e: Exception) {
            portalDao.getAllPortalData()
        }
    }
}