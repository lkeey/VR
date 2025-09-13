package dev.vr.com.presentation.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.vr.com.core.components.button.RoundedButton
import dev.vr.com.core.components.field.VRDropDown
import dev.vr.com.core.components.field.VRTextField
import dev.vr.com.core.theme.Theme
import dev.vr.com.data.model.CategoryModel
import org.jetbrains.compose.resources.Font
import vr.composeapp.generated.resources.Bold
import vr.composeapp.generated.resources.ExtraBold
import vr.composeapp.generated.resources.Res
import java.awt.Frame
import java.io.File
import java.awt.FileDialog as AwtFileDialog

@Composable
fun SettingsScreen (
    viewModel: SettingsViewModel
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .clip(
                GenericShape { size, _ ->
                    // Размер среза угла (можешь регулировать)
                    val cutX = size.width * 0.05f
                    val cutY = size.height * 0.1f

                    moveTo(0f, 0f)                 // левый верх
                    lineTo(size.width, 0f)         // правый верх
                    lineTo(size.width, size.height - cutY) // правая сторона вниз
                    lineTo(size.width - cutX, size.height) // срезанный угол
                    lineTo(0f, size.height)        // левый низ
                    close()
                }
            )
            .background(Theme.colors.secondaryGray)
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        state.error?.let {
            Text(
                text = "Ошибка: $it",
                color = Theme.colors.pinkAction,
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                fontWeight = FontWeight(800),
            )
        }

        RoundedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            color = Theme.colors.pinkAction,
            content = {
                Text(
                    text = "Добавить что-то новое можно здесь",
                    color = Theme.colors.textInverse,
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                    fontWeight = FontWeight(800),
                )
            },
            innerPadding = PaddingValues(
                vertical = 4.dp
            )
        ) { }

        VRDropDown(
            categories = CategoryModel.entries.toList(),
            previousData = state.gameCategory?.key ?: "",
            label = "Выберите категорию (раздел)",
            onOptionSelected = {
                viewModel.onEvent(SettingsEvent.OnChoseGameCategory(it))
            }
        )

        VRTextField(
            previousData = state.gameName ?: "",
            label = "Введите название игры / блока:",
            onTextChanged = {
                viewModel.onEvent(SettingsEvent.OnEnterGameName(it))
            }
        )

        VRTextField(
            previousData = state.gameName ?: "",
            label = "Введите описание игры / блока, если есть:",
            onTextChanged = {
                viewModel.onEvent(SettingsEvent.OnEnterGameDescription(it))
            }
        )

        VRTextField(
            previousData = state.gameMovieUrl ?: "",
            label = "**Введите ссылку на видео игры / блока, если есть:",
            onTextChanged = {
                viewModel.onEvent(SettingsEvent.OnEnterGameMoviePath(it))
            }
        )


        Button(onClick = {
            val file = chooseImageFile()
            viewModel.onEvent(SettingsEvent.OnChooseImage(file))
        }) {
            Text("Выберите изображение")
        }

        state.gameImage?.let {
            Image(
                bitmap = it,
                contentDescription = "Selected Image",
                modifier = Modifier
                    .size(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        RoundedButton(
            color = Theme.colors.pinkAction,
            content = {
                Text(
                    text = "ДОБАВИТЬ",
                    color = Theme.colors.textInverse,
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(Res.font.Bold)),
                    fontWeight = FontWeight(700)
                )
            },
            innerPadding = PaddingValues(
                horizontal = 40.dp,
                vertical = 8.dp
            )
        ) {
            viewModel.onEvent(SettingsEvent.OnAddGame)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            thickness = 2.dp,
            color = Theme.colors.blueAction
        )

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
    }
}

fun chooseImageFile(): File? {
    val fileDialog = AwtFileDialog(null as Frame?, "Select Image", AwtFileDialog.LOAD)
    fileDialog.isVisible = true
    return if (fileDialog.file != null) File(fileDialog.directory, fileDialog.file) else null
}

