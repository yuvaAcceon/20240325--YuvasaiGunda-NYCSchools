package com.yuvasai.nycschools.presentation.school_details

import com.yuvasai.nycschools.domain.model.SchoolDetails

data class SchoolDetailsState(
    val isLoading: Boolean = false,
    val schoolDetails: SchoolDetails? = null,
    val error: String = ""
)
