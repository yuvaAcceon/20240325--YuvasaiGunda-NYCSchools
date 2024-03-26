package com.yuvasai.nycschools.domain.use_case.get_school

import com.google.common.truth.Truth.assertThat
import com.yuvasai.nycschools.data.remote.dto.SchoolDetailsDTO
import com.yuvasai.nycschools.data.repository.FakeSchoolDirectoryRepository
import com.yuvasai.nycschools.domain.DataError
import com.yuvasai.nycschools.domain.Result
import com.yuvasai.nycschools.domain.RootError
import com.yuvasai.nycschools.domain.model.SchoolDetails
import com.yuvasai.nycschools.domain.repository.SchoolDirectoryRepository
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import retrofit2.Response

class GetSchoolDetailsUseCaseTest {

    private lateinit var getSchoolDetailsUseCase: GetSchoolDetailsUseCase
    private lateinit var fakeSchoolDirectoryRepository: FakeSchoolDirectoryRepository
    private lateinit var schoolDetailsDTO: SchoolDetailsDTO

    private lateinit var repository: SchoolDirectoryRepository
    private lateinit var useCase: GetSchoolDetailsUseCase

    @Before
    fun setUp() {
        fakeSchoolDirectoryRepository = FakeSchoolDirectoryRepository()
        getSchoolDetailsUseCase = GetSchoolDetailsUseCase(fakeSchoolDirectoryRepository)

        repository = mock(SchoolDirectoryRepository::class.java)
        useCase = GetSchoolDetailsUseCase(repository)
    }

    @Test
    fun `test successful retrieval`() {
        schoolDetailsDTO = SchoolDetailsDTO(
            dbn = "dbn",
            schoolName = "schoolName",
            numOfSatTestTakers = "1",
            satMathAvgScore = "2",
            satCriticalReadingAvgScore = "3",
            satWritingAvgScore = "4"
        )
        fakeSchoolDirectoryRepository.schoolDetailsList.add(schoolDetailsDTO)
        getSchoolDetailsUseCase.invoke("dbn").onEach {
            assertThat(it).isEqualTo(
                Result.Success<SchoolDetails, RootError>(
                    SchoolDetails(
                        dbn = schoolDetailsDTO.dbn,
                        schoolName = schoolDetailsDTO.schoolName,
                        satTestTakersCount = schoolDetailsDTO.numOfSatTestTakers,
                        satAvgMathScore = schoolDetailsDTO.satMathAvgScore,
                        satAvgReadingScore = schoolDetailsDTO.satCriticalReadingAvgScore,
                        satAvgWritingScore = schoolDetailsDTO.satWritingAvgScore
                    )
                )
            )
        }
    }

    @Test
    fun `test empty list retrieval`() {
        getSchoolDetailsUseCase.invoke("dbn").onEach {
            assertThat(it).isEqualTo(
                Result.Error<SchoolDetails, RootError>(DataError.Local.ITEM_NOT_FOUND)
            )
        }
    }

    @Test
    fun `test Http error`(): Unit = runBlocking {
        // Mock network error from repository
        `when`(repository.getSchoolDetails("DBN")).thenThrow(HttpException(Response.success("")))

        // Invoke the use case
        getSchoolDetailsUseCase.invoke("dbn").onEach {
            assertThat(it).isEqualTo(
                Result.Error<SchoolDetails, RootError>(DataError.Network.SERVER_ERROR)
            )
        }
    }

    @Test
    fun `test Unknown error`(): Unit = runBlocking {
        // Mock network error from repository
        `when`(repository.getSchoolDetails("DBN")).thenThrow(NullPointerException())

        // Invoke the use case
        getSchoolDetailsUseCase.invoke("dbn").onEach {
            assertThat(it).isEqualTo(
                Result.Error<SchoolDetails, RootError>(DataError.Network.UNKNOWN)
            )
        }
    }

    fun tearDown() {}

    fun testInvoke() {}
}