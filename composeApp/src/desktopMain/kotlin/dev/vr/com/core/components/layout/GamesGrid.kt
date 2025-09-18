package dev.vr.com.core.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vr.com.core.components.item.GridItem
import dev.vr.com.presentation.model.GameModel

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
        itemsIndexed(games) { index, game ->
            GridItem(
                modifier = Modifier,
                game = game,
                index = index,
                isEnableToDelete = isEnableToDelete,
                onClick = {
                    onClick(game)
                }
            )
        }
    }
}
