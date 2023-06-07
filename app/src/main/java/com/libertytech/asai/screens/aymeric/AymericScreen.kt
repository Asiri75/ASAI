package com.libertytech.asai.screens.aymeric


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
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
import androidx.compose.material.Button
import androidx.compose.material.TextField


@Composable
fun AymericScreen(
    activityViewModel: ActivityViewModel = viewModel()
) {
    val activityUiState by activityViewModel.uiState.collectAsState()
    AymericLayout(activityViewModel = activityViewModel, activityUiState = activityUiState)
}
@Composable
fun AymericLayout(
    activityViewModel: ActivityViewModel,
    activityUiState: ActivityUiState
) {
    val activityDescription = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Donne moi 5 activités à faire sur Paris : ",
            style = typography.h1,
            fontSize = 25.sp
        )
        TextField(
            value = activityDescription.value,
            onValueChange = { activityDescription.value = it },
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { activityViewModel.makeRequest(activityDescription.value.text) }
            ) {
                Text(
                    text = "Demander",
                    fontSize = 16.sp
                )
            }
        }
        Text(text = activityUiState.searchFinalResult, fontSize = 16.sp, style = typography.body1)
    }
}