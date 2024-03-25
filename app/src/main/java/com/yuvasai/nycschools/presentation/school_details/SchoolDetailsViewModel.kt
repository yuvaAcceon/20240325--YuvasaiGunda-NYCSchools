package com.yuvasai.nycschools.presentation.school_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuvasai.nycschools.common.Constants
import com.yuvasai.nycschools.domain.DataError
import com.yuvasai.nycschools.domain.Result
import com.yuvasai.nycschools.domain.use_case.get_school.GetSchoolDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(
    private val getSchoolDetailsUseCase: GetSchoolDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(SchoolDetailsState())
    val state: State<SchoolDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_DBN)?.let { dbn ->
            getSchoolDetails(dbn)
        }
    }

    private fun getSchoolDetails(dbn: String) {
        getSchoolDetailsUseCase(dbn).onEach { result ->
            when (result) {
                is Result.Error -> {
                    when (result.error) {
                        DataError.Network.SERVER_ERROR -> _state.value = SchoolDetailsState(
                            error = "Unable to connect to Server"
                        )

                        DataError.Local.ITEM_NOT_FOUND -> _state.value = SchoolDetailsState(
                            error = "Unable to fetch school details"
                        )

                        else -> _state.value = SchoolDetailsState(
                            error = "Unknown error occurred"
                        )
                    }
                }

                Result.Loading -> _state.value = SchoolDetailsState(
                    isLoading = true
                )

                is Result.Success -> _state.value = SchoolDetailsState(
                    schoolDetails = result.data
                )
            }
        }.launchIn(viewModelScope)
    }
}