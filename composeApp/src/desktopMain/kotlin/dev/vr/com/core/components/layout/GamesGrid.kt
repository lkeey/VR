package dev.vr.com.core.components.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.vr.com.presentation.model.GameModel
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.ic_open

@Composable
fun GamesGrid (
    modifier: Modifier = Modifier,
    games: List<GameModel>,
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
                        onClick(game)
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
