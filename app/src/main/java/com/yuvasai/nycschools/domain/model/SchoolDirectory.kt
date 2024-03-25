package com.yuvasai.nycschools.domain.model

data class SchoolDirectory(
    val dbn: String,
    val schoolName: String,
    val schoolEmail: String,
    val totalStudents: String,
    val website: String,
    val address: String,
    val city: String,
    val stateCode: String,
    val zip: String
)
