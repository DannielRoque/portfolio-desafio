package com.example.feednoticias.portal.business

import com.example.feednoticias.business.portal.PortalBusinessImpl
import com.example.feednoticias.data.entity.PortalViewData
import com.example.feednoticias.data.repository.portal.PortalRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class PortalBusinessImplTest {

    private lateinit var portalBusiness: PortalBusinessImpl
    private lateinit var portalRepository: PortalRepository

    @Before
    fun setup() {
        portalRepository = mock(PortalRepository::class.java)
        portalBusiness = PortalBusinessImpl(portalRepository)
    }

    @Test
    fun loadInitialFeed_should_filter_and_map_data_correctly() = runTest {

        val mockData = listOf(
            PortalViewData("1", "Título 1", "Descrição 1", "https://imagem1.jpg", null, "2023-01-01", "Cartola", "basico"),
            PortalViewData("2", "Título 2", "Descrição 2", "https://imagem2.jpg", "https://google.com", "2023-01-02", "url2", "materia"),
            PortalViewData("3", "Título 3", "Descrição 3", "https://imagem3.jpg", null, "2023-01-03", "Boné", "outro")
        )

        `when`(portalRepository.getInitialFeed()).thenReturn(mockData)

        val result = portalBusiness.loadInitialFeed()

        val expected = listOf(
            PortalViewData("1", "Título 1", "Descrição 1", "https://imagem1.jpg", "https://google.com", "2023-01-01", "Cartola", "basico"),
            PortalViewData("2", "Título 2", "Descrição 2", "https://imagem2.jpg", "https://google.com", "2023-01-02", "Boné", "materia")
        )
        assertEquals(expected, result)
        verify(portalRepository).getInitialFeed()
    }

    @Test
    fun loadNextPage_should_filter_and_map_data_correctly() = runTest {

        val mockData = listOf(
            PortalViewData("1", "Título 1", "Descrição 1", "https://imagem1.jpg", null, "2023-01-01", "Chapeu de Pagodeiro", "materia"),
            PortalViewData("2", "Título 2", "Descrição 2", "https://imagem2.jpg", null, "2023-01-02", "Chapeu de Praia", "outro")
        )

        `when`(portalRepository.getPaginetedFeed(2)).thenReturn(mockData)

        val result = portalBusiness.loadNextPage(2)

        val expected = listOf(
            PortalViewData("1", "Título 1", "Descrição 1", "https://imagem1.jpg", "https://google.com", "2023-01-01", "Chapeu do Ronaldinho", "materia")
        )
        assertEquals(expected, result)
        verify(portalRepository).getPaginetedFeed(2)
    }

    @Test
    fun loadInitialFeed_should_return_empty_list_when_repository_returns_no_data() = runTest {

        `when`(portalRepository.getInitialFeed()).thenReturn(emptyList())

        val result = portalBusiness.loadInitialFeed()

        assertEquals(emptyList<PortalViewData>(), result)
        verify(portalRepository).getInitialFeed()
    }

    @Test
    fun loadNextPage_should_return_empty_list_when_repository_returns_no_data() = runTest {

        `when`(portalRepository.getPaginetedFeed(3)).thenReturn(emptyList())

        val result = portalBusiness.loadNextPage(3)

        assertEquals(emptyList<PortalViewData>(), result)
        verify(portalRepository).getPaginetedFeed(3)
    }
}
