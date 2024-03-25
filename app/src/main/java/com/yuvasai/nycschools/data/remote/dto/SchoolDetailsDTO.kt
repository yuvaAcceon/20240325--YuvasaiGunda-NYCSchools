package com.yuvasai.nycschools.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.yuvasai.nycschools.domain.model.SchoolDetails

data class SchoolDetailsDTO(
    val dbn: String,
    @SerializedName("num_of_sat_test_takers")
    val numOfSatTestTakers: String,
    @SerializedName("sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String,
    @SerializedName("sat_math_avg_score")
    val satMathAvgScore: String,
    @SerializedName("sat_writing_avg_score")
    val satWritingAvgScore: String,
    @SerializedName("school_name")
    val schoolName: String
)

fun SchoolDetailsDTO.toSchoolDetails(): SchoolDetails {
    return SchoolDetails(
        dbn = dbn,
        schoolName = schoolName,
        satTestTakersCount = numOfSatTestTakers,
        satAvgMathScore = satMathAvgScore,
        satAvgReadingScore = satCriticalReadingAvgScore,
        satAvgWritingScore = satWritingAvgScore
    )
}