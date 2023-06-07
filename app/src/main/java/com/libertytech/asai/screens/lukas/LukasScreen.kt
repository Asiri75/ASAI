package com.libertytech.asai.screens

import LukasUiState
import LukasViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun LukasScreen(
        lukasViewModel: LukasViewModel = viewModel()
) {
    val lukasUiState by lukasViewModel.uiState.collectAsState()
    LukasLayout(lukasViewModel = lukasViewModel, lukasUiState = lukasUiState)
}

@Composable
fun LukasLayout(
    lukasViewModel: LukasViewModel,
    lukasUiState: LukasUiState,
) {
    val searchedRoadTrip = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .padding(16.dp),
    ) {
        Text(
            text = "Créer un roadtrip",
            style = typography.h1,
            fontSize = 25.sp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            TextField(
                value = searchedRoadTrip.value,
                onValueChange = { searchedRoadTrip.value = it },
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { lukasViewModel.makeRequest(searchedRoadTrip.value.text) }
            ) {
                Text(
                    text = "Demander",
                    fontSize = 16.sp
                )
            }

            if (lukasUiState.searchFinalResult.isNotEmpty()) {
                RoadTripContent(destinations = lukasUiState.searchFinalResult)
            }
        }
    }
}

@Composable
fun RoadTripContent(destinations: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(
            items = destinations,
            itemContent = {
                if (it != "") {
                    DestinationListItem(destination = it)
                }
            })
    }
}

@Composable
fun DestinationListItem(destination: String) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                Text(text = destination, style = typography.h6)
            }
        }
    }
}