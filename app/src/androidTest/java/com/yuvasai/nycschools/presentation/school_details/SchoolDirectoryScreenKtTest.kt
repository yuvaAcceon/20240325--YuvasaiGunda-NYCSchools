package com.yuvasai.nycschools.presentation.school_details

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.yuvasai.nycschools.common.TestTags
import com.yuvasai.nycschools.data.di.AppModule
import com.yuvasai.nycschools.presentation.MainActivity
import com.yuvasai.nycschools.presentation.Screen
import com.yuvasai.nycschools.presentation.school_directory.SchoolDirectoryScreen
import com.yuvasai.nycschools.presentation.ui.theme.NYCSchoolsTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class SchoolDirectoryScreenKtTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            NYCSchoolsTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.SchoolDirectoryScreen.route
                ) {
                    composable(
                        route = Screen.SchoolDirectoryScreen.route
                    ) {
                        SchoolDirectoryScreen(navController)
                    }
                }
            }
        }
    }

    @Test
    fun toolbar_NYCSchools_visible() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(TestTags.TOOL_BAR_TEXT).assertDoesNotExist()
    }

}