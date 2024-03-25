@file:OptIn(ExperimentalMaterial3Api::class)

package com.yuvasai.nycschools.presentation.school_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolDetailsScreen(
    viewModel: SchoolDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.schoolDetails?.let { schoolDetails ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
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
                        Text(
                            schoolDetails.schoolName,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "SAT Test Taken Students: ",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        modifier = Modifier.fillMaxWidth(),
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = schoolDetails.satTestTakersCount,
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Average SAT Math Score of Students: ",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        modifier = Modifier.fillMaxWidth(),
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = schoolDetails.satAvgMathScore,
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Average SAT Reading Score of Students: ",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        modifier = Modifier.fillMaxWidth(),
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = schoolDetails.satAvgReadingScore,
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Average SAT Writing Score of Students: ",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        modifier = Modifier.fillMaxWidth(),
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = schoolDetails.satAvgWritingScore,
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}