package com.example.mymarvel.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mymarvel.data.online.model.Comic
import com.example.mymarvel.data.repo.IComicRepo
import com.example.mymarvel.util.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var mockComicRepo: IComicRepo

    @Mock
    private lateinit var mockViewDataObserver: Observer<Comic?>

    @Mock
    private lateinit var mockEventsObserver: Observer<SearchViewModel.Event>

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        viewModel = SearchViewModel(mockComicRepo)
        viewModel.viewData.observeForever(mockViewDataObserver)
        viewModel.events.observeForever(mockEventsObserver)
    }

    @Test
    fun search_whenQueryIsNull_thenTest_viewData() = testCoroutineRule.runBlockingTest{
        viewModel.search(null)
        verify(mockViewDataObserver).onChanged(null)
    }

    @Test
    fun search_whenQueryIsEmpty_thenTest_viewData() = testCoroutineRule.runBlockingTest{
        viewModel.search("")
        verify(mockViewDataObserver).onChanged(null)
    }

    @Test
    fun search_whenQueryReturnsError_thenTest_comicRepoAnd_viewDataAnd_events() = testCoroutineRule.runBlockingTest{
        val fakeError = Exception("fake error")
        `when`(mockComicRepo.getComicById("9988")).thenThrow(fakeError)
        viewModel.search("9988")
        verify(mockComicRepo).getComicById("9988")
        verify(mockViewDataObserver).onChanged(null)
        verify(mockEventsObserver).onChanged(SearchViewModel.Event.SearchError(fakeError.message!!))
    }

    @Test
    fun search_whenQueryReturnsSuccess_thenTest_comicRepoAnd_viewDataAnd_events() = testCoroutineRule.runBlockingTest{
        val testData = Comic(
            "fake comic", "fake description",
            Comic.ImageData("fake path", "fake extension")
        )
        `when`(mockComicRepo.getComicById("99888")).thenReturn(testData)
        viewModel.search("99888")
        verify(mockComicRepo).getComicById("99888")
        verify(mockViewDataObserver).onChanged(testData)
        verify(mockEventsObserver).onChanged(SearchViewModel.Event.LoadingStatus(false))
    }
}