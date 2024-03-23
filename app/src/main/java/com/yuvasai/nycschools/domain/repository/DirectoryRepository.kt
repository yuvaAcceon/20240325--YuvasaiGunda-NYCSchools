package com.yuvasai.nycschools.domain.repository

import com.yuvasai.nycschools.data.remote.dto.Directory

interface DirectoryRepository {
    suspend fun getDirectory(limit: Int, offset: Int): List<Directory>
}