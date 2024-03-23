package com.yuvasai.nycschools.domain.model

data class DirectoryItem(
    val dbn: String,
    val schoolName: String,
    val schoolEmail: String,
    val stateCode: String,
    val totalStudents: String,
    val website: String,
    val zip: String
)
