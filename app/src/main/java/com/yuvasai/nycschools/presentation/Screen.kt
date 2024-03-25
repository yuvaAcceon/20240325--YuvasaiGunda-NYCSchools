package com.yuvasai.nycschools.presentation

sealed class Screen(val route: String) {
    data object SchoolDirectoryScreen : Screen("school_directory_screen")
    data object SchoolDetailsScreen : Screen("school_details_screen")
}