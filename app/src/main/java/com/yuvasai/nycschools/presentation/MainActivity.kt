package com.yuvasai.nycschools.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuvasai.nycschools.presentation.school_details.SchoolDetailsScreen
import com.yuvasai.nycschools.presentation.school_directory.SchoolDirectoryScreen
import com.yuvasai.nycschools.presentation.ui.theme.NYCSchoolsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYCSchoolsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SchoolDirectoryScreen.route
                    ) {
                        composable(
                            route = Screen.SchoolDirectoryScreen.route
                        ) {
                            SchoolDirectoryScreen(navController)
                        }
                        composable(
                            route = Screen.SchoolDetailsScreen.route + "/{dbn}"
                        ) {
                            SchoolDetailsScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NYCSchoolsTheme {
        Greeting("Android")
    }
}