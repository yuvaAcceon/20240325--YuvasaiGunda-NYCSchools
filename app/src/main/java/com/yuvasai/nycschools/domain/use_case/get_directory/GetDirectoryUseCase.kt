package com.yuvasai.nycschools.domain.use_case.get_directory

import com.yuvasai.nycschools.data.remote.dto.Directory
import com.yuvasai.nycschools.domain.DataError
import com.yuvasai.nycschools.domain.Result
import com.yuvasai.nycschools.domain.repository.DirectoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDirectoryUseCase @Inject constructor(
    private val repository: DirectoryRepository
) {

    operator fun invoke(): Flow<Result<List<Directory>, DataError>> = flow {

    }
}