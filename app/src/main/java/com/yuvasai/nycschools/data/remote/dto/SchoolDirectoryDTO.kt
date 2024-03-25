package com.yuvasai.nycschools.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.yuvasai.nycschools.domain.model.SchoolDirectory

data class SchoolDirectoryDTO(
    @SerializedName("academicopportunities1")
    val academicOpportunities1: String,
    @SerializedName("academicopportunities2")
    val academicOpportunities2: String,
    @SerializedName("admissionspriority11")
    val admissionsPriority11: String,
    @SerializedName("admissionspriority21")
    val admissionsPriority21: String,
    @SerializedName("admissionspriority31")
    val admissionsPriority31: String,
    @SerializedName("attendance_rate")
    val attendanceRate: String,
    val bbl: String,
    val bin: String,
    val boro: String,
    val borough: String,
    @SerializedName("building_code")
    val buildingCode: String,
    val bus: String,
    @SerializedName("census_tract")
    val censusTract: String,
    val city: String?,
    val code1: String,
    @SerializedName("community_board")
    val communityBoard: String,
    @SerializedName("council_district")
    val councilDistrict: String,
    val dbn: String,
    val directions1: String,
    @SerializedName("ell_programs")
    val ellPrograms: String,
    @SerializedName("extracurricular_activities")
    val extracurricularActivities: String,
    @SerializedName("fax_number")
    val faxNumber: String,
    @SerializedName("finalgrades")
    val finalGrades: String,
    @SerializedName("grade9geapplicants1")
    val grade9geApplicants1: String,
    @SerializedName("grade9geapplicantsperseat1")
    val grade9geApplicantsPerSeat1: String,
    @SerializedName("grade9gefilledflag1")
    val grade9geFilledFlag1: String,
    @SerializedName("grade9swdapplicants1")
    val grade9swdApplicants1: String,
    @SerializedName("grade9swdapplicantsperseat1")
    val grade9swdApplicantsPerSeat1: String,
    @SerializedName("grade9swdfilledflag1")
    val grade9swdFilledFlag1: String,
    val grades2018: String,
    val interest1: String,
    val latitude: String,
    val location: String,
    val longitude: String,
    val method1: String,
    val neighborhood: String,
    val nta: String,
    @SerializedName("offer_rate1")
    val offerRate1: String,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String,
    @SerializedName("pct_stu_enough_variety")
    val pctStuEnoughVariety: String,
    @SerializedName("pct_stu_safe")
    val pctStuSafe: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String?,
    val program1: String,
    @SerializedName("requirement1_1")
    val requirement11: String,
    @SerializedName("requirement2_1")
    val requirement21: String,
    @SerializedName("requirement3_1")
    val requirement31: String,
    @SerializedName("requirement4_1")
    val requirement41: String,
    @SerializedName("requirement5_1")
    val requirement51: String,
    @SerializedName("school_10th_seats")
    val school10thSeats: String,
    @SerializedName("school_accessibility_description")
    val schoolAccessibilityDescription: String,
    @SerializedName("school_email")
    val schoolEmail: String?,
    @SerializedName("school_name")
    val schoolName: String?,
    @SerializedName("school_sports")
    val schoolSports: String,
    val seats101: String,
    val seats9ge1: String,
    val seats9swd1: String,
    @SerializedName("state_code")
    val stateCode: String?,
    val subway: String,
    @SerializedName("total_students")
    val totalStudents: String?,
    val website: String?,
    val zip: String?
)

fun SchoolDirectoryDTO.toSchoolDirectoryItem(): SchoolDirectory {
    return SchoolDirectory(
        dbn = dbn,
        schoolName = schoolName ?: "Name does not exist",
        schoolEmail = schoolEmail ?: "Email does not exist",
        stateCode = stateCode ?: "State Code data does not exist",
        totalStudents = totalStudents ?: "Count does not exist",
        website = website ?: "Website does not exist",
        address = primaryAddressLine1 ?: "Address data does not exist",
        city = city ?: "City data does not exist",
        zip = zip ?: "Zip data does not exist"
    )
}