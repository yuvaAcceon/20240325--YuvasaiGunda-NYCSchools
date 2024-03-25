package com.yuvasai.nycschools.domain.use_case.get_directory

import com.yuvasai.nycschools.data.remote.dto.toSchoolDirectoryItem
import com.yuvasai.nycschools.domain.model.SchoolDirectory
import com.yuvasai.nycschools.domain.repository.SchoolDirectoryRepository
import javax.inject.Inject

class GetSchoolDirectoryUseCase @Inject constructor(
    private val repository: SchoolDirectoryRepository
) {
    suspend operator fun invoke(page: Int, pageSize: Int): Result<List<SchoolDirectory>> {
        return Result.success(
            repository.getDirectory(pageSize, page * pageSize).map { it.toSchoolDirectoryItem() }
        )
    }
}