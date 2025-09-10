package dev.vr.com.presentation.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.io.File
import javax.imageio.ImageIO
import java.awt.FileDialog as AwtFileDialog

@Composable
fun SettingsScreen (
    viewModel: GamesViewModel
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var videoPath by remember { mutableStateOf("") }
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Yellow)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        state.error?.let {
            Text("Ошибка: $it")
        }

        if (state.games.isNotEmpty()) {
            state.games.forEach {
                Column {
                    Text("$it")

                    Image(
                        bitmap = it.image,
                        contentDescription = it.text,
                    )
                }


            }
        } else {
            Text("Игр нет")
        }


        Text("Add New Game")

        BasicTextField(
            value = name,
            onValueChange = { name = it },
            decorationBox = { inner -> Box(modifier = Modifier.fillMaxWidth()) { inner() } }
        )
        Text("Game Name")

        BasicTextField(
            value = description,
            onValueChange = { description = it },
            decorationBox = { inner -> Box(modifier = Modifier.fillMaxWidth()) { inner() } }
        )
        Text("Description")

        BasicTextField(
            value = videoPath,
            onValueChange = { videoPath = it },
            decorationBox = { inner -> Box(modifier = Modifier.fillMaxWidth()) { inner() } }
        )
        Text("Video Path (e.g., video.mp4)")

        Button(onClick = {
            val file = chooseImageFile()
            if (file != null) {
                val buffered = ImageIO.read(file)
                imageBitmap = buffered.toComposeImageBitmap()
            }
        }) {
            Text("Select Image")
        }

        imageBitmap?.let {
            Image(it, contentDescription = "Selected Image", modifier = Modifier.size(150.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (name.isNotBlank() && description.isNotBlank() && videoPath.isNotBlank() && imageBitmap != null) {
                viewModel.onEvent(
                    GamesEvent.AddGame(
                        game = dev.vr.com.domain.model.GameModel(
                            text = name,
                            description = description,
                            movie = videoPath,
                            image = imageBitmap!! // will convert later to ByteArray
                        )
                    )
                )
                // Clear fields
                name = ""
                description = ""
                videoPath = ""
                imageBitmap = null
            }
        }) {
            Text("Add Game")
        }
    }
}

fun chooseImageFile(): File? {
    val fileDialog = AwtFileDialog(null as java.awt.Frame?, "Select Image", AwtFileDialog.LOAD)
    fileDialog.isVisible = true
    return if (fileDialog.file != null) File(fileDialog.directory, fileDialog.file) else null
}

