import com.example.feednoticias.data.api.portal.PortalApi
import com.example.feednoticias.data.db.painel.PortalDao
import com.example.feednoticias.data.entity.PortalViewData
import com.example.feednoticias.data.repository.portal.PortalRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify

class PortalRepositoryImplTest {

    private lateinit var repository: PortalRepositoryImpl
    private lateinit var api: PortalApi
    private lateinit var dao: PortalDao

    @Before
    fun setUp() {
        api = mock(PortalApi::class.java)
        dao = mock(PortalDao::class.java)
        repository = PortalRepositoryImpl(api, dao)
    }

    @Test
    fun getInitialFeed_should_return_transformed_data_and_save_it_to_database_on_success() = runTest {

        val apiResponse = listOf(
            PortalViewData(PAGE_ID, TITLE, DESCRIPTION, EXAMPLE_URL, EXAMPLE_URL, EXAMPLE_DATE, CHAPEU, TYPE)
        )
        `when`(api.getInitialFeed()).thenReturn(apiResponse)

        val result = repository.getInitialFeed()

        verify(dao).deleteAllPortalData()
        verify(dao).insertPortalData(apiResponse)
        assertEquals(apiResponse, result)
    }

    @Test
    fun getInitialFeed_should_return_cached_data_on_error() = runTest {

        `when`(api.getInitialFeed()).thenThrow(RuntimeException("Network Error"))
        val cachedData = listOf(
            PortalViewData(PAGE_ID, TITLE, DESCRIPTION, EXAMPLE_URL, EXAMPLE_URL, EXAMPLE_DATE, CHAPEU, TYPE)
        )
        `when`(dao.getAllPortalData()).thenReturn(cachedData)

        val result = repository.getInitialFeed()

        verify(dao).getAllPortalData()
        assertEquals(cachedData, result)
    }

    @Test
    fun getPaginatedFeed_should_return_transformed_data_and_save_it_to_database_on_success() = runTest {

        val apiResponse = listOf(
            PortalViewData(PAGE_ID, TITLE, DESCRIPTION, EXAMPLE_URL, EXAMPLE_URL, EXAMPLE_DATE, CHAPEU, TYPE)
        )
        `when`(api.getPaginatedFeed(PRODUCT, INDEX_PAGE)).thenReturn(apiResponse)

        val result = repository.getPaginetedFeed(INDEX_PAGE)

        verify(dao).insertPortalData(apiResponse)
        assertEquals(apiResponse, result)
    }

    @Test
    fun getPaginatedFeed_should_return_cached_data_on_error() = runTest {

        `when`(api.getPaginatedFeed(PRODUCT, INDEX_PAGE)).thenThrow(RuntimeException("Network Error"))
        val cachedData = listOf(
            PortalViewData(PAGE_ID, TITLE, DESCRIPTION, EXAMPLE_URL, EXAMPLE_URL, EXAMPLE_DATE, CHAPEU, TYPE)
        )
        `when`(dao.getAllPortalData()).thenReturn(cachedData)

        val result = repository.getPaginetedFeed(INDEX_PAGE)

        verify(dao).getAllPortalData()
        assertEquals(cachedData, result)
    }

    companion object {
        private const val TITLE = "Exemplo de Título"
        private const val EXAMPLE_DATE = "2023-10-10"
        private const val EXAMPLE_URL = "https://g1.globo.com"
        private const val DESCRIPTION = "Descrição do exemplo"
        private const val TYPE = "modesto"
        private const val CHAPEU = "boné"
        private const val PRODUCT = "g1"
        private const val PAGE_ID = "1"
        private const val INDEX_PAGE = 1
    }
}
