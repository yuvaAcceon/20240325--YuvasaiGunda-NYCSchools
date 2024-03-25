package com.yuvasai.nycschools.presentation.school_directory

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuvasai.nycschools.domain.use_case.get_directory.DefaultPaginator
import com.yuvasai.nycschools.domain.use_case.get_directory.GetSchoolDirectoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class SchoolDirectoryViewModel @Inject constructor(
    private val getDirectoryUseCase: GetSchoolDirectoryUseCase
) : ViewModel() {
    var state by mutableStateOf(SchoolDirectoryState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            getDirectoryUseCase(nextPage, 20)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            val message = when (it) {
                is HttpException -> "Failed to fetch data from the server. Please try again later."
                is UnknownHostException -> "Unable to establish a network connection. Please check your internet connection and try again."
                is IOException -> "An error occurred while communicating with the server. Please try again later."
                else -> it?.localizedMessage ?: "An unknown error occurred. Please try again later."
            }
            state = state.copy(error = message)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}