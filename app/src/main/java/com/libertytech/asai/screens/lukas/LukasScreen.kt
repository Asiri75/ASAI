package com.libertytech.asai.screens

import LukasViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LukasScreen(
        modifier: Modifier = Modifier,
        lukasViewModel: LukasViewModel = viewModel()
) {
    val lukasUiState by lukasViewModel.uiState.collectAsState()
    LukasLayout(lukasViewModel= lukasViewModel, currentScrambledWord = lukasUiState.currentScrambledWord)
}

@Composable
fun LukasLayout(
        lukasViewModel: LukasViewModel,
        currentScrambledWord: String,
        modifier: Modifier = Modifier,
) {
    Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
                text = currentScrambledWord,
                fontSize = 45.sp,
                modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Button(onClick = { lukasViewModel.handleClick() }) {
            Text(text = "Click Me !")
        }
        
    }
}