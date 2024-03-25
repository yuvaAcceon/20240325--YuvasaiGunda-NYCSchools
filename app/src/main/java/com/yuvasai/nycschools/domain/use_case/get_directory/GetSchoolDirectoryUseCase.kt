package com.yuvasai.nycschools.domain.use_case.get_directory

import com.yuvasai.nycschools.data.remote.dto.toSchoolDirectoryItem
import com.yuvasai.nycschools.domain.model.SchoolDirectory
import com.yuvasai.nycschools.domain.repository.SchoolDirectoryRepository
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class GetSchoolDirectoryUseCase @Inject constructor(
    private val repository: SchoolDirectoryRepository
) {
    suspend operator fun invoke(page: Int, pageSize: Int): Result<List<SchoolDirectory>> {
        try {
            return Result.success(
                repository.getDirectory(pageSize, page * pageSize)
                    .map { it.toSchoolDirectoryItem() }
            )
        } catch (e: HttpException) {
            return Result.failure(e)
        } catch (e: UnknownHostException) {
            return Result.failure(e)
        } catch (e: IOException) {
            return Result.failure(e)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}