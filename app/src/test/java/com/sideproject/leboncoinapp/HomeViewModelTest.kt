package com.sideproject.leboncoinapp

import com.sideproject.data.album.entity.toAlbum
import com.sideproject.domain.models.album.Resource
import com.sideproject.domain.usecase.GetAlbumsUseCase
import com.sideproject.leboncoinapp.MockTestUtil.mockAlbumList
import com.sideproject.leboncoinapp.ui.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    private var getLocalAlbumsUseCase = mock(GetAlbumsUseCase::class.java)

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(getLocalAlbumsUseCase)
    }

    @Test
    fun getLocalAlbumsSuccessTest() = coroutinesRule.testScope.runTest {
        // Arrange
        val albums = mockAlbumList()
        `when`(getLocalAlbumsUseCase.invoke()).thenReturn(flowOf(Resource.Success(albums.map { it.toAlbum() })))

        // Act
        viewModel.getLocalAlbums()

        // Assert
        verify(getLocalAlbumsUseCase)()
        viewModel._uiState.collect { result ->
            assert(result is Resource.Success)
            assert((result as Resource.Success).data == albums)
        }
    }

    @Test
    fun getLocalAlbumsErrorTest() = coroutinesRule.testScope.runTest {
        // Arrange
        val errorMessage = "An error occurred"
        `when`(getLocalAlbumsUseCase.invoke()).thenReturn(flowOf(Resource.Error(Throwable(errorMessage))))

        // Act
        viewModel.getLocalAlbums()

        // Assert
        verify(getLocalAlbumsUseCase)()
        viewModel._uiState.collect { result ->
            assert(result is Resource.Error)
            assert((result as Resource.Error).throwable.message == errorMessage)
        }
    }
}
