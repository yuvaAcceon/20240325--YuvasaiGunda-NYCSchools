package com.yuvasai.nycschools.data.remote

import com.yuvasai.nycschools.data.remote.dto.Directory
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectoryAPI {
    @GET("/s3k6-pzi2.json")
    suspend fun getDirectory(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): List<Directory>
}