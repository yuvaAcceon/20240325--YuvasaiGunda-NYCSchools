package com.yuvasai.nycschools.domain.use_case.get_school

import android.os.Build
import android.os.ext.SdkExtensions
import com.yuvasai.nycschools.data.remote.dto.toSchoolDetails
import com.yuvasai.nycschools.domain.DataError
import com.yuvasai.nycschools.domain.Result
import com.yuvasai.nycschools.domain.model.SchoolDetails
import com.yuvasai.nycschools.domain.repository.SchoolDirectoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetSchoolDetailsUseCase @Inject constructor(
    private val repository: SchoolDirectoryRepository
) {

    operator fun invoke(dbn: String): Flow<Result<SchoolDetails, DataError>> = flow {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                Build.VERSION_CODES.S
            ) >= 7
        ) {
            try {
                emit(Result.Loading)
                val schools = repository.getSchoolDetails(dbn).map { it.toSchoolDetails() }
                if (schools.isNotEmpty())
                    emit(Result.Success(schools[0]))
                else
                    emit(Result.Error(DataError.Local.ITEM_NOT_FOUND))
            } catch (e: HttpException) {
                emit(Result.Error(DataError.Network.SERVER_ERROR))
                e.printStackTrace()
            } catch (e: Exception) {
                emit(Result.Error(DataError.Network.UNKNOWN))
            }
        }
    }
}