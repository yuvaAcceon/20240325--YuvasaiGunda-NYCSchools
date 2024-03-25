package com.yuvasai.nycschools.domain.repository

import com.yuvasai.nycschools.data.remote.dto.SchoolDetailsDTO
import com.yuvasai.nycschools.data.remote.dto.SchoolDirectoryDTO

interface SchoolDirectoryRepository {
    suspend fun getDirectory(limit: Int, offset: Int): List<SchoolDirectoryDTO>

    suspend fun getSchoolDetails(dbn: String): List<SchoolDetailsDTO>
}