package com.yuvasai.nycschools.data.repository

import com.yuvasai.nycschools.data.remote.SchoolDirectoryAPI
import com.yuvasai.nycschools.data.remote.dto.SchoolDetailsDTO
import com.yuvasai.nycschools.data.remote.dto.SchoolDirectoryDTO
import com.yuvasai.nycschools.domain.repository.SchoolDirectoryRepository
import javax.inject.Inject

class SchoolDirectoryRepositoryImpl @Inject constructor(
    private val api: SchoolDirectoryAPI
) : SchoolDirectoryRepository {
    override suspend fun getDirectory(limit: Int, offset: Int): List<SchoolDirectoryDTO> {
        return api.getDirectory(limit, offset)
    }

    override suspend fun getSchoolDetails(dbn: String): List<SchoolDetailsDTO> {
        return api.getSchoolDetails(dbn)
    }
}