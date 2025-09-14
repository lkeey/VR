package dev.vr.com.core.components.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.vr.com.core.theme.Theme
import dev.vr.com.presentation.model.GameModel
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.ic_open

@Composable
fun GamesGrid (
    modifier: Modifier = Modifier,
    games: List<GameModel>,
    isEnableToDelete: Boolean = false,
    onClick: (GameModel) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells
            .Fixed(7),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(games) { game ->
            Box (
                modifier = Modifier
                    .clickable (
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (!isEnableToDelete) {
                            onClick(game)
                        }
                    }
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 250.dp),
                    bitmap = game.image,
                    contentDescription = game.text,
                    contentScale = ContentScale.Crop
                )

                if (isEnableToDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "open",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.BottomEnd)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(Theme.colors.pinkAction)
                            .padding(4.dp)
                            .clickable (
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                onClick(game)
                            },
                        tint = Color.White
                    )

                } else {
                    Icon(
                        painter = painterResource(Res.drawable.ic_open),
                        contentDescription = "open",
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(4.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}
