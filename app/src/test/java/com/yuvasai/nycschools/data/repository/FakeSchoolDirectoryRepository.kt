package com.yuvasai.nycschools.data.repository

import com.yuvasai.nycschools.data.remote.dto.SchoolDetailsDTO
import com.yuvasai.nycschools.data.remote.dto.SchoolDirectoryDTO
import com.yuvasai.nycschools.domain.repository.SchoolDirectoryRepository

class FakeSchoolDirectoryRepository : SchoolDirectoryRepository {
    val schoolDirectory = mutableListOf<SchoolDirectoryDTO>()
    val schoolDetailsList = mutableListOf<SchoolDetailsDTO>()
    override suspend fun getDirectory(limit: Int, offset: Int): List<SchoolDirectoryDTO> {
        return schoolDirectory
    }

    override suspend fun getSchoolDetails(dbn: String): List<SchoolDetailsDTO> {
        return schoolDetailsList
    }
}