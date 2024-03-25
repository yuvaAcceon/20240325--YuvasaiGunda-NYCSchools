@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.yuvasai.nycschools.presentation.school_directory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yuvasai.nycschools.R
import com.yuvasai.nycschools.presentation.Screen
import com.yuvasai.nycschools.presentation.school_directory.components.SchoolDirectoryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolDirectoryScreen(
    navController: NavController,
    viewModel: SchoolDirectoryViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column {
        TopAppBar(
            modifier = Modifier.shadow(
                elevation = 20.dp,
                spotColor = Color.DarkGray
            ),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(stringResource(R.string.nyc_schools))
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            items(state.items.size) { i ->
                val item = state.items[i]
                if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                    viewModel.loadNextItems()
                }
                Spacer(modifier = Modifier.height(16.dp))
                SchoolDirectoryItem(school = item, onItemClick = {
                    navController.navigate(Screen.SchoolDetailsScreen.route + "/${item.dbn}")
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                if (state.isLoading) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}