package dev.vr.com.core.components.field


import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dev.vr.com.core.theme.Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VRDropDown (
    options: List<String>,
    previousData: String,
    label : String,
    modifier: Modifier = Modifier,
    onOptionSelected: (String) -> Unit,
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    var textValue by remember {
        mutableStateOf(previousData)
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        VRTextField(
            previousData = textValue,
            label = textValue.ifEmpty { label },
            isReadOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            onTextChanged = { }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    content = {
                        Text(
                            text = option,
                            color = Theme.colors.pinkAction
                        )
                    },
                    onClick = {
                        textValue = option

                        expanded = false
                        onOptionSelected(option)
                    },
                )
            }
        }
    }

}
