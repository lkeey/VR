package dev.vr.com.core.components.field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vr.com.core.components.corner.SkewedCutParallelogramShape
import dev.vr.com.core.theme.Theme
import org.jetbrains.compose.resources.Font
import vr.composeapp.generated.resources.Bold
import vr.composeapp.generated.resources.Res

@Composable
fun VRTextField (
    previousData: String,
    label: String,
    backgroundColor: Color = Theme.colors.grayBackground,
    isReadOnly: Boolean = false,
    skew: Dp = 12.dp,
    cutTL: Dp = 8.dp,
    cutTR: Dp = 8.dp,
    cutBR: Dp = 20.dp,
    trailingIcon: @Composable (() -> Unit)? = null,
    onTextChanged: (String) -> Unit,
) {
    var textValue by remember {
        mutableStateOf(previousData)
    }

    TextField(
        value = textValue,
        onValueChange = {
            textValue = it
            onTextChanged(it)
        },
        readOnly = isReadOnly,
        placeholder = {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Theme.colors.blueAction,
                    fontFamily = FontFamily(Font(Res.font.Bold)),
                )
            )
        },
        shape = SkewedCutParallelogramShape(
            skew = skew,
            cutTopLeft = cutTL,
            cutTopRight = cutTR,
            cutBottomRight = cutBR
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Theme.colors.pinkAction,
            cursorColor = Theme.colors.pinkAction,
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = trailingIcon
    )

}
