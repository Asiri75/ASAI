package com.libertytech.asai.screens

import LukasUiState
import LukasViewModel
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


@Composable
fun LukasScreen(
    lukasViewModel: LukasViewModel = viewModel()
) {
    val lukasUiState by lukasViewModel.uiState.collectAsState()
    LukasLayout(lukasViewModel = lukasViewModel, lukasUiState = lukasUiState)
}

@Composable
fun LukasLayout(
    lukasViewModel: LukasViewModel,
    lukasUiState: LukasUiState,
) {
    val searchedRoadTrip = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Créer un roadtrip",
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
                value = searchedRoadTrip.value,
                onValueChange = { searchedRoadTrip.value = it },
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { lukasViewModel.makeRequest(searchedRoadTrip.value.text) }
            ) {
                Text(
                    text = "Demander",
                    fontSize = 16.sp
                )
            }
        }
        Text(text = lukasUiState.searchFinalResult, fontSize = 16.sp, style = typography.body1)
    }
}