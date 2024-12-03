import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.feednoticias.business.portal.PortalBusiness
import com.example.feednoticias.data.entity.PortalViewData
import com.example.feednoticias.presentation.portal.viewstate.PortalState
import com.example.feednoticias.presentation.portal.viewmodel.PortalViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PortalViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: PortalViewModel
    private val business: PortalBusiness = mock()
    private val observer: Observer<PortalState> = mock()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PortalViewModel(mock(), business)
        viewModel.feedState.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadInitialFeed_should_emit_Loading_and_Success_states() = runTest {
        val mockData = listOf(mockPortalViewData("Noticias"), mockPortalViewData("Esporte"))
        whenever(business.loadInitialFeed()).thenReturn(mockData)

        viewModel.loadInitialFeed()
        advanceUntilIdle()

        inOrder(observer) {
            verify(observer).onChanged(PortalState.Loading(true))
            verify(observer).onChanged(PortalState.Success(mockData))
            verify(observer).onChanged(PortalState.Loading(false))
        }
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun loadInitialFeed_should_emit_Loading_and_Error_states_on_failure() = runTest {

        whenever(business.loadInitialFeed()).thenThrow(RuntimeException("Network error"))

        viewModel.loadInitialFeed()
        advanceUntilIdle()

        inOrder(observer) {
            verify(observer).onChanged(PortalState.Loading(true))
            verify(observer).onChanged(PortalState.Loading(false))
        }
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun loadNextPage_should_append_new_data_to_existing_feed() = runTest {

        val initialData = listOf(mockPortalViewData("Noticias1"))
        val newPageData = listOf(mockPortalViewData("Noticias2"))
        whenever(business.loadInitialFeed()).thenReturn(initialData)
        whenever(business.loadNextPage(1)).thenReturn(newPageData)

        viewModel.loadInitialFeed()
        advanceUntilIdle()

        viewModel.loadNextPage()
        advanceUntilIdle()

        inOrder(observer) {
            verify(observer).onChanged(PortalState.Loading(true))
            verify(observer).onChanged(PortalState.Success(initialData))
            verify(observer).onChanged(PortalState.Loading(false))
            verify(observer).onChanged(PortalState.Loading(true))
            verify(observer).onChanged(PortalState.Success(initialData + newPageData))
            verify(observer).onChanged(PortalState.Loading(false))
        }
        verifyNoMoreInteractions(observer)
    }

    private fun mockPortalViewData(title: String) = mock<PortalViewData> {
        on { this.titulo } doReturn title
    }
}
