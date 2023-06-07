package com.libertytech.asai.screens

import BrandNameUiState
import BrandNameViewModel
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
fun MariusScreen(
    brandNameViewModel: BrandNameViewModel = viewModel()
) {
    val brandNameStateUi by brandNameViewModel.uiState.collectAsState()
    BrandNameLayout(brandNameViewModel = brandNameViewModel, brandNameStateUi = brandNameStateUi)
}

@Composable
fun BrandNameLayout(
    brandNameViewModel: BrandNameViewModel,
    brandNameStateUi: BrandNameUiState,
) {
    val searchedBrandName = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Générateur de nom d'entreprise",
            style = typography.h1,
            fontSize = 20.sp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = searchedBrandName.value,
                onValueChange = { searchedBrandName.value = it },
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { brandNameViewModel.makeRequest(searchedBrandName.value.text) }
            ) {
                Text(
                    text = "C'est parti !",
                    fontSize = 15.sp
                )
            }
        }
        Text(text = brandNameStateUi.searchFinalResult, fontSize = 16.sp, style = typography.body1)
    }
}