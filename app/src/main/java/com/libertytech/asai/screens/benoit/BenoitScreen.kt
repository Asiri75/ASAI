package com.libertytech.asai.screens.benoit

import android.service.autofill.FillEventHistory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun BenoitScreen(benoitViewModel: BenoitViewModel = viewModel()){
    val benoitStateUI by benoitViewModel.uiState.collectAsState()
    
    BenoitLayout(benoitViewModel = benoitViewModel, currentResponse = benoitStateUI.title, history = benoitStateUI.history)
}

@Composable
fun BenoitLayout(benoitViewModel: BenoitViewModel, currentResponse: String, history: Array<String>) {
    val textPostState = remember { mutableStateOf(TextFieldValue()) }
    val textXPState = remember { mutableStateOf(TextFieldValue()) }

        LazyColumn(
            modifier = Modifier.padding(16.dp).padding(bottom = 60.dp)
        ) {
            item {
                Text(
                    text = currentResponse,
                    fontSize = 45.sp
                )
                TextField(
                    value = textPostState.value,
                    onValueChange = { textPostState.value = it },
                    modifier = Modifier.padding(bottom = 16.dp),
                    label = { Text("Poste") }
                )
                TextField(
                    value = textXPState.value,
                    onValueChange = { textXPState.value = it },
                    modifier = Modifier.padding(bottom = 16.dp),
                    label = { Text("Année d'expérience") }
                )
                Button(
                    onClick = { benoitViewModel.makeRequest(textPostState.value.text, textXPState.value.text) },
                    modifier = Modifier.padding(bottom = 90.dp)
                ) {
                    Text(text = "Clique moi dessus")
                }
                Text(text = "Historique", modifier = Modifier.size(25.dp))
            }

            items(history.size) { index ->
                Text(text = "${history.get(index)}")
            }
        }
    }

