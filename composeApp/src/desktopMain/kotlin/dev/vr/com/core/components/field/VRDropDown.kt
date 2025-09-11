package dev.vr.com.core.components.field


import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dev.vr.com.core.theme.Theme
import dev.vr.com.data.model.CategoryModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VRDropDown (
    categories: List<CategoryModel>,
    previousData: String,
    label : String,
    modifier: Modifier = Modifier,
    onOptionSelected: (CategoryModel) -> Unit,
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
            categories.forEach { category ->
                DropdownMenuItem(
                    content = {
                        Text(
                            text = category.key,
                            color = Theme.colors.pinkAction
                        )
                    },
                    onClick = {
                        textValue = category.key

                        expanded = false
                        onOptionSelected(category)
                    },
                )
            }
        }
    }

}
