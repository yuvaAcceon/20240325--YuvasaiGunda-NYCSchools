package com.yuvasai.nycschools.data.repository

import com.yuvasai.nycschools.data.remote.DirectoryAPI
import com.yuvasai.nycschools.data.remote.dto.Directory
import com.yuvasai.nycschools.domain.repository.DirectoryRepository
import javax.inject.Inject

class DirectoryRepositoryImpl @Inject constructor(
    private val api: DirectoryAPI
) : DirectoryRepository {
    override suspend fun getDirectory(limit: Int, offset: Int): List<Directory> {
        return api.getDirectory(,)
    }
}