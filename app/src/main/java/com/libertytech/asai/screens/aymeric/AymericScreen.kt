package com.libertytech.asai.screens.aymeric

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AymericScreen(aymericViewModel: ActivityViewModel = viewModel()) {
   val aymericStateUi by aymericViewModel.uiState.collectAsState()
    ActivityLayout(currentTitle = aymericStateUi.currentScrambledWord)
}
@Composable
fun ActivityLayout(currentTitle: String){
    Text(text = currentTitle)

}
