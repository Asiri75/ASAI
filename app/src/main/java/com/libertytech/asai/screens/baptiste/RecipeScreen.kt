package com.libertytech.asai.screens.baptiste

import RecipeViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
//Made by Baptiste 07/06/2023
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun RecipeScreen(recipeViewModel: RecipeViewModel = viewModel()) {
    val recipeStateUI by recipeViewModel.uiState.collectAsState()

    RecipeLayout(
        currentTitle = recipeStateUI.currentScrambledWord,
        recipeViewModel = recipeViewModel
    )
}
//Made by Baptiste 07/06/2023
@Composable
fun RecipeLayout(recipeViewModel: RecipeViewModel, currentTitle: String, modifier: Modifier = Modifier) {
    val isLoading by remember { recipeViewModel.isLoading }
    var textInput by remember { mutableStateOf(recipeViewModel.textInput) }
    val isPopupVisible = remember { mutableStateOf(false) }

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
                        text = "Entre ton plat !",
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
            // When loading, display CircularProgressIndicator
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    modifier = Modifier.padding(start = 8.dp)
                )
            } else {
                IconButton(
                    onClick = {
                        recipeViewModel.handleClick()
                        isPopupVisible.value = true
                    },
                    modifier = Modifier.padding(start = 8.dp),
                    //Important : If textInput isEmpty then button is disabled !
                    enabled = textInput.isNotEmpty()
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        //Important : If textInput is Empty, then button is gray !
                        tint = if (textInput.isNotEmpty()) Color.Blue else Color.Gray
                    )
                }
            }
        }
    }

    if (!isLoading && isPopupVisible.value) {
        AlertPopup(onDismiss = { isPopupVisible.value = false })
    }
}
//Made by Baptiste 07/06/2023

@Composable
fun AlertPopup(onDismiss: () -> Unit) {
    val videoId = "dQw4w9WgXcQ"
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Hop !")
        },
        text = {
            YoutubeScreen(videoId = videoId, modifier = Modifier.fillMaxSize())
            Text(text = "Votre liste d'ingrédients est prête")
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(text = "Merci!")
            }
        }
    )
}

@Composable
fun YoutubeScreen(
    videoId: String,
    modifier: Modifier
) {
    val ctx = LocalContext.current
    AndroidView(factory = {
        var view = YouTubePlayerView(it)
        val fragment = view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            }
        )
        view
    })
}