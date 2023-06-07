package com.libertytech.asai.screens.baptiste

import RecipeViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun RecipeScreen(recipeViewModel: RecipeViewModel = viewModel()) {
    val recipeStateUI by recipeViewModel.uiState.collectAsState()

    RecipeLayout(
        currentTitle = recipeStateUI.currentScrambledWord,
        recipeViewModel = recipeViewModel
    )
}

@Composable
fun RecipeLayout(recipeViewModel: RecipeViewModel, currentTitle: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = currentTitle,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        val isLoading by remember { recipeViewModel.isLoading }
        var textInput by remember { mutableStateOf(recipeViewModel.textInput) }

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = textInput,
                onValueChange = { textInputValue ->
                    textInput = textInputValue
                    recipeViewModel.textInput = textInputValue
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 15.dp),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                label = {
                    Text(
                        text = "Enter ingredients",
                        color = Color.Gray,
                        style = TextStyle(fontSize = 14.sp)
                    )
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.LightGray,
                    cursorColor = Color.Blue
                )
            )

            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    modifier = Modifier.padding(start = 8.dp)
                )
            } else {
                IconButton(
                    onClick = { recipeViewModel.handleClick() },
                    modifier = Modifier.padding(start = 8.dp),
                    enabled = textInput.isNotEmpty()
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = if (textInput.isNotEmpty()) Color.Blue else Color.Gray
                    )
                }
            }
        }
    }
}