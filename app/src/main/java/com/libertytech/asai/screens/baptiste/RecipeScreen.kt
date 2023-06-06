package com.libertytech.asai.screens.baptiste

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun RecipeScreen(recipeViewModel: RecipeViewModel = viewModel()){
    val recipeStateUI by recipeViewModel.uiState.collectAsState()

    RecipeLayout(currentTitle = recipeStateUI.currentScrambledWord, recipeViewModel = recipeViewModel)
}

@Composable
fun RecipeLayout(recipeViewModel: RecipeViewModel, currentTitle: String, modifier: Modifier = Modifier,) {

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = currentTitle,
            fontSize = 45.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Button(onClick = { recipeViewModel.handleClick() }) {
            Text(text = "Click me")
        }
    }
}