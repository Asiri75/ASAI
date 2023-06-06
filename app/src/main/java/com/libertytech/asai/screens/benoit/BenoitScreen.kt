package com.libertytech.asai.screens.benoit

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun BenoitScreen(benoitViewModel: BenoitViewModel = viewModel()){
    val benoitStateUI by benoitViewModel.uiState.collectAsState()
    
    BenoitLayout(currentTitle = benoitStateUI.title)
}

@Composable
fun BenoitLayout(currentTitle: String ){
    Text(text = currentTitle, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp))
}