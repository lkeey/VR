package dev.vr.com.presentation.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vr.com.core.components.button.RoundedButton
import dev.vr.com.core.components.field.VRTextField
import dev.vr.com.core.components.overlay.VRPopUp
import dev.vr.com.core.theme.Theme
import org.jetbrains.compose.resources.Font
import vr.composeapp.generated.resources.Bold
import vr.composeapp.generated.resources.ExtraBold
import vr.composeapp.generated.resources.Res

@Composable
fun SettingsGate(
    correctPin: String = "2025",
    viewModel: SettingsViewModel,
    onDismiss: () -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }
    var enteredPin by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    if (showDialog) {
        VRPopUp(
            onDismiss = onDismiss
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                error?.let {
                    Text(
                        text = "Ошибка: $it",
                        color = Theme.colors.pinkAction,
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                        fontWeight = FontWeight(800),
                    )
                }

                VRTextField(
                    previousData = "",
                    label = "Введите PIN-код:",
                    onTextChanged = {
                        enteredPin = it
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoundedButton(
                    color = Theme.colors.pinkAction,
                    content = {
                        Text(
                            text = "ДАЛЕЕ",
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
                    if (enteredPin == correctPin) {
                        showDialog = false
                    } else {
                        error = "Неверный PIN"
                    }
                }
            }
        }
    } else {
        SettingsScreen(viewModel = viewModel)
    }
}
