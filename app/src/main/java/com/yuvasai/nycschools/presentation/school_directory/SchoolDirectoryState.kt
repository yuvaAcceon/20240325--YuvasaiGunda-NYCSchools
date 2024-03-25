package com.yuvasai.nycschools.presentation.school_directory

import com.yuvasai.nycschools.domain.model.SchoolDirectory

/*data class SchoolDirectoryState(
    val isLoading: Boolean = false,
    var listItems: List<SchoolDirectory> = emptyList(),
    val error: String = ""
)*/

data class SchoolDirectoryState(
    val isLoading: Boolean = false,
    val items: List<SchoolDirectory> = emptyList(),
    val error: String? = "",
    val endReached: Boolean = false,
    val page: Int = 0
)
