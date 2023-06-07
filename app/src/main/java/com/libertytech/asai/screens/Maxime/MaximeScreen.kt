package com.libertytech.asai.screens.Maxime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.libertytech.asai.screens.maxime.MovieUIState

@Composable
fun MaximeScreen(
    MaximeViewModel: MaximeViewModel = viewModel()
) {
    val movieUiState by MaximeViewModel.uiState.collectAsState()
    MaximeLayout(maximeViewModel = MaximeViewModel, movieUiState = movieUiState)
}
@Composable
fun MaximeLayout(
    maximeViewModel: MaximeViewModel,
    movieUiState: MovieUIState
) {
    val generatorMovie = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Liste de films",
            style = typography.h1,
            fontSize = 25.sp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = generatorMovie.value,
                onValueChange = { generatorMovie.value = it },
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { MaximeViewModel.makeRequest(generatorMovie.value.text) }
            ) {
                Text(
                    text = "Demander",
                    fontSize = 16.sp
                )
            }
        }
        Text(text = MovieUiState.finalResult, fontSize = 16.sp, style = typography.body1)
    }
}