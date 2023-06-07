package com.libertytech.asai.screens

import MariusViewModel
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
fun MariusScreen(
        modifier: Modifier = Modifier,
        mariusViewModel: MariusViewModel = viewModel()
) {
    val mariusUiState by mariusViewModel.uiState.collectAsState()
    MariusLayout(mariusViewModel= mariusViewModel, currentScrambledWord = mariusUiState.currentScrambledWord)
}

@Composable
fun MariusLayout(
        mariusViewModel: MariusViewModel,
        currentScrambledWord: String,
        modifier: Modifier = Modifier,
) {
    Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
                text = currentScrambledWord,
                fontSize = 40.sp,
                modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Button(onClick = { mariusViewModel.handleClick() }) {
            Text(text = "Touche moi !")
        }

    }
}