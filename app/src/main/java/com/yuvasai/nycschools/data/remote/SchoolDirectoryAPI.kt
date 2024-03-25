package com.yuvasai.nycschools.data.remote

import com.yuvasai.nycschools.data.remote.dto.SchoolDetailsDTO
import com.yuvasai.nycschools.data.remote.dto.SchoolDirectoryDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SchoolDirectoryAPI {
    @Headers("Content-Type:application/json")
    @GET("s3k6-pzi2.json")
    suspend fun getDirectory(
        @Query("\$limit") limit: Int,
        @Query("\$offset") offset: Int
    ): List<SchoolDirectoryDTO>

    @Headers("Content-Type:application/json")
    @GET("f9bf-2cp4.json")
    suspend fun getSchoolDetails(
        @Query("dbn") dbn: String
    ): List<SchoolDetailsDTO>
}