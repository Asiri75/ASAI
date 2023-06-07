package com.libertytech.asai.screens.Maxime

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MaximeScreen(
    modifier: Modifier = Modifier,
    maximeViewModel: MaximeViewModel = viewModel()
) {

    val movieUiState by maximeViewModel.uiState.collectAsState()
    Text(text = movieUiState)

}