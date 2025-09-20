package dev.vr.com.core.components.overlay

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.vr.com.core.components.button.CloseButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VRPopUp(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Dialog(
        onDismissRequest = {
            // тут нельзя вызывать Composable
            // поэтому просто запускаем suspend-логику через helper
            onDismissWithAnimation(
                scope = scope,
                onDismiss = onDismiss,
                setVisible = { visible = false }
            )
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight } // сверху вниз
            ) + fadeIn(),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight } // вниз
            ) + fadeOut()
        ) {
            Row {
                content()

                CloseButton(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    onDismissWithAnimation(
                        scope = scope,
                        onDismiss = onDismiss,
                        setVisible = { visible = false }
                    )
                }
            }
        }
    }
}

private fun onDismissWithAnimation(
    scope: CoroutineScope,
    onDismiss: () -> Unit,
    setVisible: (() -> Unit)? = null,
    duration: Long = 300
) {
    setVisible?.invoke()

    scope.launch {
        delay(duration)
        onDismiss()
    }
}
